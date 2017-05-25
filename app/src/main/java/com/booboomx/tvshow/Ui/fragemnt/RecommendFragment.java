package com.booboomx.tvshow.Ui.fragemnt;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.booboomx.tvshow.R;
import com.booboomx.tvshow.Ui.adapter.RecommendAdapter;
import com.booboomx.tvshow.Utils.SystemUtils;
import com.booboomx.tvshow.base.BaseLazyLoadFragment;
import com.booboomx.tvshow.bean.Banner;
import com.booboomx.tvshow.bean.Recommend;
import com.booboomx.tvshow.mvp.presenter.RecommendPresenter;
import com.booboomx.tvshow.mvp.view.IRecommendView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 推荐
 */
public class RecommendFragment extends BaseLazyLoadFragment<IRecommendView,RecommendPresenter> implements IRecommendView{


    private ConvenientBanner<Banner>mConvenientBanner;
    private TextView tvTips;
    @BindView(R.id.recyclerView)
   public EasyRecyclerView mRecyclerView;
    private RecommendAdapter mAdapter;
    private List<Recommend.RoomBean>listData;
    private List<Banner>listBanner;
    public  static RecommendFragment newInstance(){
        Bundle bundle=new Bundle();
        RecommendFragment fragment=new RecommendFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getFragmentId() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initUI() {
         tvTips = (TextView) mRecyclerView.findViewById(R.id.tvTips);
        SpaceDecoration decoration=new SpaceDecoration(0);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setRefreshingColorResources(R.color.progress_color);


        listData=new ArrayList<>();
        mAdapter=new RecommendAdapter(getContext(),listData);


        listBanner=new ArrayList<>();
        mAdapter.addHeader(new HeaderView(listBanner));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getRecommend();
            }
        });

    }

    @Override
    public void initData() {
        mRecyclerView.showProgress();
        getPresenter().getRecommend();
        getPresenter().getBanner();

    }

    @Override
    public void setListener() {

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
    public void onGetRecommend(Recommend recommend) {


    }

    @Override
    public void onGetRooms(List<Recommend.RoomBean> list) {

        mAdapter.addAll(list);
        mAdapter.notifyDataSetChanged();
        if(mAdapter.getCount()==0){
            mRecyclerView.showEmpty();
        }

    }

    @Override
    public void onGetBanner(List<Banner> list) {

        if (mConvenientBanner != null) {
            toSetList(listBanner,list,false);
            mConvenientBanner.notifyDataSetChanged();
        }

    }
    @NonNull
    @Override
    public RecommendPresenter createPresenter() {
        return new RecommendPresenter(getApp());
    }


    public class  HeaderView implements RecyclerArrayAdapter.ItemView{

        public HeaderView(List<Banner> listBanner) {
            this.listBanner = listBanner;
        }

        private List<Banner>listBanner;


        @Override
        public View onCreateView(ViewGroup parent) {
            View v= LayoutInflater.from(getContext()).inflate(R.layout.item_banner,parent,false);

            mConvenientBanner= (ConvenientBanner<Banner>) v.findViewById(R.id.convenientBanner);

            mConvenientBanner.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    clickBannerItem(listBanner.get(position));

                }
            });

            return v;
        }



        @Override
        public void onBindView(View headerView) {

            mConvenientBanner.setPages(new CBViewHolderCreator() {
                @Override
                public Holder<Banner> createHolder() {

                    return new ImageHolder();
                }
            },listBanner)
                    .setPageIndicator(new int[]{R.drawable.ic_dot_normal,R.drawable.ic_dot_pressed})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);



            if(!mConvenientBanner.isTurning()){
                mConvenientBanner.startTurning(4000);
            }

        }
    }


    public class  ImageHolder implements Holder<Banner>{

        private ImageView iv;

        @Override
        public View createView(Context context) {
            iv=new ImageView(context);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            return iv;
        }

        @Override
        public void UpdateUI(Context context, int position, Banner data) {


            Glide.with(context)
                    .load(data.getThumb())
                    .placeholder(R.mipmap.live_default)
                    .error(R.mipmap.live_default)
                    .centerCrop()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv);


        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mConvenientBanner!=null&&!mConvenientBanner.isTurning()){
            mConvenientBanner.startTurning(4000);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mConvenientBanner != null) {
            mConvenientBanner.stopTurning();
        }
    }


    private void clickBannerItem(Banner banner){
        if(banner!=null){
            if(banner.isRoom()){//如果是房间类型就点击进入房间
                startRoom(banner.getLink_object());
            }else{//广告类型
                startWeb(banner.getTitle(),banner.getLink());
            }
        }
    }
}
