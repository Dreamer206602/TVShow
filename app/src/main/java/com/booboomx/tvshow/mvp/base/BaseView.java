package com.booboomx.tvshow.mvp.base;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by booboomx on 17/5/18.
 */

public interface BaseView extends MvpView{
    void showProgress();

    void onCompleted();

    void onError(Throwable e);

}
