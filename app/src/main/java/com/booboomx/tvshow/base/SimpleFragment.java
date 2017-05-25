package com.booboomx.tvshow.base;

import com.booboomx.tvshow.mvp.base.BasePresenter;
import com.booboomx.tvshow.mvp.base.BaseView;

/**
 * Created by booboomx on 17/5/19.
 */

public abstract class SimpleFragment extends BaseLazyLoadFragment<BaseView,BasePresenter<BaseView>> {


    @Override
    public BasePresenter<BaseView> createPresenter() {
        return new BasePresenter<>(getApp());
    }
}
