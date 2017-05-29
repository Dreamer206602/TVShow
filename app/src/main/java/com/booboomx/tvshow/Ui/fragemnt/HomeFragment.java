package com.booboomx.tvshow.Ui.fragemnt;


import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.base.BaseLazyLoadFragment;
import com.booboomx.tvshow.bean.LiveCategory;
import com.booboomx.tvshow.http.Constants;
import com.booboomx.tvshow.mvp.presenter.CategoryPresenter;
import com.booboomx.tvshow.mvp.view.ICategoryView;
import com.king.base.adapter.ViewPagerFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */
public class HomeFragment extends BaseLazyLoadFragment<ICategoryView, CategoryPresenter> implements ICategoryView {

    public static final String TAG = HomeFragment.class.getSimpleName();
    @BindView(R.id.ivLeft)
    ImageView mIvLeft;
    @BindView(R.id.ivTitle)
    ImageView mIvTitle;
    @BindView(R.id.ivRight)
    ImageView mIvRight;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.btnMore)
    ImageView mBtnMore;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private ViewPagerFragmentAdapter mFragmentAdapter;
    private List<LiveCategory>listCategory;
    private List<CharSequence>listTitle;
    private List<Fragment>listData;


    @Override
    public int getFragmentId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI() {


        listCategory=new ArrayList<>();
        listTitle=new ArrayList<>();
        listData=new ArrayList<>();

        mFragmentAdapter=new ViewPagerFragmentAdapter(getChildFragmentManager(),listData,listTitle);
        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void initData() {
        Log.d(TAG, "initData: HomeFragment");
        getPresenter().getAllCategories();

    }

    @Override
    public void setListener() {


    }


    @NonNull
    @Override
    public CategoryPresenter createPresenter() {
        return new CategoryPresenter(getApp());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onGetLiveCategory(List<LiveCategory> list) {


        if (list != null) {
            toSetList(listCategory,list,false);

            listData.clear();
            List<CharSequence>listTemp=new ArrayList<>();


            listTemp.add(getText(R.string.recommend));
            listData.add(RecommendFragment.newInstance());


            listTemp.add(getText(R.string.tab_all));
            listData.add(LiveListFragment.newInstance(null));


            for (int i = 0; i < list.size(); i++) {

                LiveCategory liveCategory = list.get(i);

                listTemp.add(liveCategory.getName());
                listData.add(LiveListFragment.newInstance(liveCategory.getSlug()));

            }

            toSetList(listTitle,listTemp,false);

        }


        if (mFragmentAdapter != null) {
            mFragmentAdapter.setListTitle(listTitle);
            mFragmentAdapter.setListData(listData);
            mFragmentAdapter.notifyDataSetChanged();

        }

    }


    @OnClick({R.id.ivLeft, R.id.ivRight, R.id.btnMore,R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                startActivity(getFragmentIntent(Constants.SEARCH_FRAGMENT));
                break;
            case R.id.ivRight:
                startLogin();
                break;
            case R.id.btnMore:
                break;
            case R.id.fab:
                startAbout();
                break;

        }
    }




}
