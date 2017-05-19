package com.booboomx.tvshow.mvp.base;

import com.booboomx.tvshow.app.App;
import com.booboomx.tvshow.dagger.component.AppComponent;
import com.booboomx.tvshow.dao.greendao.DaoSession;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

/**
 * Created by booboomx on 17/5/18.
 */

public class BasePresenter<V extends BaseView> extends MvpBasePresenter<V> {

    private App app;
    private DaoSession mDaoSession;
    private AppComponent mAppComponent;

    @Inject
    public BasePresenter(App app){
        super();
        this.app=app;
//        mDaoSession=app.getDaoSession();
//        mAppComponent=app.getAppComponent();
    }
//
//    public App getApp() {
//        return mApp;
//    }
//
//    public DaoSession getDaoSession() {
//        return mDaoSession;
//    }

//    public AppComponent getAppComponent() {
//        return mAppComponent;
//    }
}
