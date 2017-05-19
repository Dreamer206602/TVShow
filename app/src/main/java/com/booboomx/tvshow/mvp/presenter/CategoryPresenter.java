package com.booboomx.tvshow.mvp.presenter;

import android.util.Log;

import com.booboomx.tvshow.app.App;
import com.booboomx.tvshow.http.APIRetrofit;
import com.booboomx.tvshow.mvp.base.BasePresenter;
import com.booboomx.tvshow.bean.LiveCategory;
import com.booboomx.tvshow.mvp.view.ICategoryView;
import com.booboomx.tvshow.thread.ThreadPoolManager;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by booboomx on 17/5/18.
 */

public class CategoryPresenter extends BasePresenter<ICategoryView> {
    
    public static final String TAG=CategoryPresenter.class.getSimpleName();
    public CategoryPresenter(App app) {
        super(app);
    }


    public void  getAllCategories(){

        getView().showProgress();
        APIRetrofit.getAPIService()
//        getAppComponent().getAPIService()
                .getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<LiveCategory>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Log.i(TAG, "onStart: ");
                    }

                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                        if(isViewAttached())
                            getView().onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i(TAG, "onError: "+e.getMessage());
                        if(isViewAttached())
                            getView().onError(e);

                    }

                    @Override
                    public void onNext(final List<LiveCategory> liveCategories) {


                        Log.i(TAG, "onNext: ");
                        ThreadPoolManager.getInstance().execute(new Runnable() {
                            @Override
                            public void run() {

//                                getDaoSession().getLiveCategoryDao().insertOrReplaceInTx(liveCategories);

                            }
                        });


                        if(isViewAttached())
                            getView().onGetLiveCategory(liveCategories);

                    }
                });

    }


}
