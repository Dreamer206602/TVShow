package com.booboomx.tvshow.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.booboomx.tvshow.app.App;
import com.booboomx.tvshow.mvp.base.BasePresenter;
import com.booboomx.tvshow.mvp.base.BaseView;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.king.base.BaseFragment;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by booboomx on 17/5/15.
 */

public abstract class BaseLazyLoadFragment<V extends BaseView,P extends BasePresenter<V>> extends MvpFragment<V,P> {

    /**
     * 视图是否已经初初始化
     */
    protected boolean isInit = false;
    protected boolean isLoad = false;
    public static final String TAG=BaseLazyLoadFragment.class.getSimpleName();

    private View mView;

    private Unbinder mUnbinder;
    public abstract  int getFragmentId();
    public abstract  void initUI();
    public abstract  void  initData();
    public abstract  void  setListener();

    public App getApp(){
        return (App) getActivity().getApplication();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView=inflater.inflate(getFragmentId(),container,false);
        mUnbinder= ButterKnife.bind(this,mView);
        isInit=true;

        /**初始化的时候去加载数据**/
        isCanLoadData();
        return mView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }
    private void isCanLoadData() {

        Log.d(TAG, "isCanLoadData: getUserVisibleHint()->"+getUserVisibleHint());
        if(!isInit){
            return;
        }

        if(getUserVisibleHint()){
            lazyLoad();
            isLoad=true;
        }else{
            if(isLoad){
                stopLoad();
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit=false;
        isLoad=false;
    }

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以覆写此方法
     */
    protected  void stopLoad(){

    }

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    protected  void lazyLoad(){
        initData();
        initUI();
        setListener();

    }



    public void replaceChildFragment(@IdRes int id, Fragment fragment){
        getChildFragmentManager().beginTransaction().replace(id,fragment).commit();
    }



    public <T> void  toSetList(List<T> list, List<T> newList, boolean isMore){
        if(list!=null && newList!=null){
            synchronized (BaseFragment.class){
                if(!isMore){
                    list.clear();
                }
                list.addAll(newList);
            }
        }
    }


    @Override
    public void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }
}
