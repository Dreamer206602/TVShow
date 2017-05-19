package com.booboomx.tvshow.Ui.fragemnt;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.base.BaseLazyLoadFragment;
import com.booboomx.tvshow.mvp.presenter.CategoryPresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseLazyLoadFragment {

    @Override
    public int getFragmentId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {

        Log.i(TAG, "initData: MineFragment");


    }

    @Override
    public void setListener() {

    }

    @NonNull
    @Override
    public MvpPresenter createPresenter() {
        return new CategoryPresenter(getApp());
    }
}
