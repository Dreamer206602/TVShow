package com.booboomx.tvshow.mvp.presenter;

import com.booboomx.tvshow.app.App;
import com.booboomx.tvshow.http.APIRetrofit;
import com.booboomx.tvshow.mvp.base.BasePresenter;
import com.booboomx.tvshow.bean.AppStart;
import com.booboomx.tvshow.bean.Recommend;
import com.booboomx.tvshow.mvp.view.IRecommendView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by booboomx on 17/5/18.
 */

public class RecommendPresenter extends BasePresenter<IRecommendView> {

    public RecommendPresenter(App app) {
        super(app);
    }

    public void getRecommend(){

        if(isViewAttached()){
            getView().showProgress();
        }
        APIRetrofit.getAPIService()
                .getRecommend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Recommend>() {
                    @Override
                    public void onCompleted() {

                        if(isViewAttached())
                            getView().onCompleted();

                    }

                    @Override
                    public void onError(Throwable e) {

                        if(isViewAttached())
                            getView().onError(e);

                    }

                    @Override
                    public void onNext(Recommend recommend) {

                        if(isViewAttached())
                            getView().onGetRecommend(recommend);


                        if (recommend != null) {

                            if(isViewAttached()){
                                getView().onGetRooms(recommend.getRoom());
                            }

                        }

                    }
                });

    }


    public void getBanner(){
        APIRetrofit.getAPIService()
                .getAppStartInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AppStart>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AppStart appStart) {
                        if(appStart!=null){
                            if(isViewAttached())
                                getView().onGetBanner(appStart.getBanners());
                        }
                    }

                });
    }



}
