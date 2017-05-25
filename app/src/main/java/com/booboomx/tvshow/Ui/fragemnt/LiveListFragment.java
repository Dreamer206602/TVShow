package com.booboomx.tvshow.Ui.fragemnt;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.Ui.adapter.EasyLiveAdapter;
import com.booboomx.tvshow.Utils.DensityUtil;
import com.booboomx.tvshow.Utils.SystemUtils;
import com.booboomx.tvshow.base.BaseLazyLoadFragment;
import com.booboomx.tvshow.bean.LiveInfo;
import com.booboomx.tvshow.bean.P;
import com.booboomx.tvshow.mvp.presenter.LiveListPresenter;
import com.booboomx.tvshow.mvp.view.ILiveListView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.king.base.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveListFragment extends BaseLazyLoadFragment<ILiveListView, LiveListPresenter> implements ILiveListView {

    private View loadMore;
    private TextView tvEmpty;
    private TextView tvTips;

    private List<LiveInfo> listData;

    private EasyLiveAdapter mAdapter;

    @BindView(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;

    private String slug;
    private boolean isMore;
    private boolean isSearch;
    private int page;
    private String key;

    public static LiveListFragment newInstance(String slug) {

        return newInstance(slug, false);
    }

    public static LiveListFragment newInstance(String slug, boolean isSearch) {

        Bundle bundle = new Bundle();

        LiveListFragment fragment = new LiveListFragment();
        fragment.slug = slug;
        fragment.isSearch = isSearch;
        fragment.setArguments(bundle);
        return fragment;


    }


    @Override
    public int getFragmentId() {
        return R.layout.fragment_live_list;
    }

    @Override
    public void initUI() {

        tvTips = (TextView) mRecyclerView.findViewById(R.id.tvTips);
        tvEmpty = (TextView) mRecyclerView.findViewById(R.id.tvEmpty);


        SpaceDecoration spaceDecoration = new SpaceDecoration(DensityUtil.dp2px(getContext(), 6));
        mRecyclerView.addItemDecoration(spaceDecoration);


        mRecyclerView.setRefreshingColorResources(R.color.progress_color);

        listData = new ArrayList<>();
        mAdapter = new EasyLiveAdapter(getContext(), listData, isSearch);

        mAdapter.setNotifyOnChange(false);


        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(manager);


        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isSearch) {
                    if (!StringUtils.isBlank(key)) {
                        page = 0;
                        getPresenter().getliveListByKey(key, page);
                    }
                }else {
                    getPresenter().getLiveList(slug);

                }
            }
        });

        if(isSearch){
            loadMore= LayoutInflater.from(getContext()).inflate(R.layout.load_more,null);

            mAdapter.setMore(loadMore, new RecyclerArrayAdapter.OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    if(isMore){

                        if(loadMore!=null){
                            loadMore.setVisibility(View.VISIBLE);
                        }

                        getPresenter().getliveListByKey(key,page);

                    }
                }
            });


        }



        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startRoom(mAdapter.getItem(position));

            }
        });


    }

    @Override
    public void initData() {
        if (!isSearch) {
            mRecyclerView.showProgress();
            getPresenter().getLiveList(slug);
        }


    }

    @Override
    public void setListener() {

    }

    @NonNull
    @Override
    public LiveListPresenter createPresenter() {
        return new LiveListPresenter(getApp());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

        mRecyclerView.setRefreshing(false);

    }

    @Override
    public void onError(Throwable e) {

        if(SystemUtils.isNetWorkActive(getContext())){
            tvTips.setText(R.string.page_load_failed);
        }else{
            tvTips.setText(R.string.network_unavailable);
        }
        mRecyclerView.showError();

    }

    @Override
    public void onGetLiveList(List<LiveInfo> list) {

        mAdapter.clear();
        mAdapter.addAll(list);
        refreshView();

    }

    private void refreshView() {

        mAdapter.notifyDataSetChanged();
        mRecyclerView.setRefreshing(false);
        if(mAdapter.getCount()==0){

            if(isSearch){
                if(SystemUtils.isNetWorkActive(getContext())){
                    tvEmpty.setText(R.string.can_not_find_relevant_content);
                }else{
                    tvTips.setText(R.string.network_unavailable);
                }
            }else{
                tvEmpty.setText(R.string.swipe_down_to_refresh);
            }
            mRecyclerView.showEmpty();
        }


        if(isSearch){
            if(mAdapter.getCount()>=(page+1)* P.DEFAULT_SIZE){
                page++;
                isMore=true;
            }else{
                if(loadMore!=null){
                    loadMore.setVisibility(View.VISIBLE);
                }
                isMore=false;
            }
        }

    }

    @Override
    public void onGetMoreLiveList(List<LiveInfo> list) {

        mAdapter.addAll(list);
        refreshView();

    }
}
