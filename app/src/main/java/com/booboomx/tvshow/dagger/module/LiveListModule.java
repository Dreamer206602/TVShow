package com.booboomx.tvshow.dagger.module;

import com.booboomx.tvshow.app.App;
import com.booboomx.tvshow.dagger.scope.FragmentScope;
import com.booboomx.tvshow.mvp.presenter.LiveListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by booboomx on 17/5/18.
 */
@Module
public class LiveListModule {

    private App app;

    public LiveListModule(App app){
        this.app = app;
    }

    @FragmentScope
    @Provides
    public LiveListPresenter provideLiveListPresenter(){
        return new LiveListPresenter(app);
    }

}
