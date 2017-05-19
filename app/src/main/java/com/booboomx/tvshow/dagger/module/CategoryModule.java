package com.booboomx.tvshow.dagger.module;

import com.booboomx.tvshow.app.App;
import com.booboomx.tvshow.dagger.scope.FragmentScope;
import com.booboomx.tvshow.mvp.presenter.CategoryPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by booboomx on 17/5/18.
 */
@Module
public class CategoryModule {

    private App mApp;

    public CategoryModule(App app){
        this.mApp=app;
    }


    @FragmentScope
    @Provides
    public CategoryPresenter provideCategoryPresenter(){

        return new CategoryPresenter(mApp);
    }
}
