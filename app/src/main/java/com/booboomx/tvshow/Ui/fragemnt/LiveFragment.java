package com.booboomx.tvshow.Ui.fragemnt;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.base.BaseLazyLoadFragment;
import com.booboomx.tvshow.mvp.base.BasePresenter;
import com.booboomx.tvshow.mvp.base.BaseView;

import butterknife.BindView;

/**
 * 直播的界面
 */
public class LiveFragment extends BaseLazyLoadFragment<BaseView, BasePresenter<BaseView>> {

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvTitle)
    TextView tvTitle;


    private String title;
    private String slug;
    private boolean isTabLive;

    public static LiveFragment newInstance(String title,String slug,boolean isTabLive){
        Bundle bundle=new Bundle();
        LiveFragment fragment=new LiveFragment();

        fragment.title=title;
        fragment.slug=slug;
        fragment.isTabLive=isTabLive;

        fragment.setArguments(bundle);

        return fragment;
    }


    public static final String TAG = LiveFragment.class.getSimpleName();

    @Override
    public int getFragmentId() {
        return R.layout.fragment_live;
    }

    @Override
    public void initUI() {


        tvTitle.setText(title);

        if(isTabLive){

            ivLeft.setImageResource(R.drawable.ic_top_search);


            ivRight.setVisibility(View.VISIBLE);

        }else{
            ivLeft.setImageResource(R.drawable.btn_back_selector);
            ivRight.setVisibility(View.GONE);
        }


        replaceChildFragment(R.id.container,LiveListFragment.newInstance(slug));
    }

    @Override
    public void initData() {

        Log.i(TAG, "initData: LiveFragment");

    }

    @Override
    public void setListener() {

    }


    @NonNull
    @Override
    public BasePresenter<BaseView> createPresenter() {
        return new BasePresenter<>(getApp());
    }
}
