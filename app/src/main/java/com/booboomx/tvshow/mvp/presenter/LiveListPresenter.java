package com.booboomx.tvshow.mvp.presenter;

import com.booboomx.tvshow.app.App;
import com.booboomx.tvshow.bean.LiveInfo;
import com.booboomx.tvshow.bean.LiveListResult;
import com.booboomx.tvshow.bean.P;
import com.booboomx.tvshow.bean.SearchRequestBody;
import com.booboomx.tvshow.bean.SearchResult;
import com.booboomx.tvshow.http.APIRetrofit;
import com.booboomx.tvshow.mvp.base.BasePresenter;
import com.booboomx.tvshow.mvp.view.ILiveListView;
import com.king.base.util.LogUtils;
import com.king.base.util.StringUtils;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by booboomx on 17/5/19.
 */

public class LiveListPresenter extends BasePresenter<ILiveListView> {


    public LiveListPresenter(App app) {
        super(app);
    }
    public void getLiveList(String slug){
        if(StringUtils.isBlank(slug)){
            getLiveList();
        }else{
            getLiveListBySlug(slug);

        }
    }

    private void getLiveListBySlug(String slug) {

        if(isViewAttached()){
            getView().showProgress();
        }


        APIRetrofit.getAPIService()
                .getLiveListResultByCategories(slug)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LiveListResult>() {
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
                    public void onNext(LiveListResult liveListResult) {

                        List<LiveInfo>list=null;
                        if (liveListResult != null) {

                            list=liveListResult.getData();
                        }

                        if(isViewAttached())
                            getView().onGetLiveList(list);

                    }
                });

    }

    private void getLiveList() {

        if(isViewAttached()){
            getView().showProgress();
        }


        APIRetrofit.getAPIService()
                .getLiveListResult()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LiveListResult>() {
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
                    public void onNext(LiveListResult liveListResult) {

                        List<LiveInfo>list=null;
                        if (liveListResult != null) {
                            list=liveListResult.getData();
                        }
                        if(isViewAttached())
                            getView().onGetLiveList(list);
                    }
                });

    }


    public void getliveListByKey(String key,int page){

        getLiveList(key,page, P.DEFAULT_SIZE);
    }

    private void getLiveList(String key, final int page, int defaultSize) {

        if(isViewAttached()){
            getView().showProgress();
        }

        APIRetrofit.getAPIService()
                .search(SearchRequestBody.getInstance(new P(page,key,defaultSize)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<SearchResult, List<LiveInfo>>() {
                    @Override
                    public List<LiveInfo> call(SearchResult searchResult) {


                        if (searchResult != null) {
                            return searchResult.getData().getItems();
                        }else{

                        }
                        return null;
                    }
                })
                .onErrorReturn(new Func1<Throwable, List<LiveInfo>>() {
                    @Override
                    public List<LiveInfo> call(Throwable throwable) {

                        return null;
                    }
                })
                .subscribe(new Action1<List<LiveInfo>>() {
                    @Override
                    public void call(List<LiveInfo> liveInfos) {

                        if(isViewAttached()){
                            if(page>0){
                                getView().onGetMoreLiveList(liveInfos);
                            }else{
                                getView().onGetLiveList(liveInfos);
                            }
                        }

                    }
                });
    }


    public void getLiveListByKey(String key,int page){
        getLiveListByKey(key,page,P.DEFAULT_SIZE);
    }

    public void getLiveListByKey(String key, final int page, int pageSize){
        if(isViewAttached()){
            getView().showProgress();
        }
        APIRetrofit.getAPIService()
                .search(SearchRequestBody.getInstance(new P(page,key,pageSize)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<SearchResult, List<LiveInfo>>() {

                    @Override
                    public List<LiveInfo> call(SearchResult searchResult) {
                        LogUtils.d("Response:" + searchResult);
                        if(searchResult!=null){
                            if(searchResult.getData()!=null){
                                return searchResult.getData().getItems();
                            }else{
                                LogUtils.d(searchResult.toString());
                            }

                        }
                        return null;
                    }
                })
                .onErrorReturn(new Func1<Throwable, List<LiveInfo>>() {
                    @Override
                    public List<LiveInfo> call(Throwable throwable) {
                        LogUtils.w(throwable);
                        return null;
                    }
                })
                .subscribe(new Action1<List<LiveInfo>>() {
                    @Override
                    public void call(List<LiveInfo> list) {
                        if(isViewAttached()){
                            if(page>0){
                                getView().onGetMoreLiveList(list);
                            }else{
                                getView().onGetLiveList(list);
                            }

                        }

                    }
                });

    }
}
