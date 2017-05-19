package com.booboomx.tvshow.dagger.component;

import com.booboomx.tvshow.Ui.fragemnt.HomeFragment;
import com.booboomx.tvshow.Ui.fragemnt.LiveListFragment;
import com.booboomx.tvshow.dagger.module.CategoryModule;
import com.booboomx.tvshow.dagger.module.LiveListModule;
import com.booboomx.tvshow.dagger.scope.FragmentScope;
import com.booboomx.tvshow.mvp.presenter.CategoryPresenter;
import com.booboomx.tvshow.mvp.presenter.LiveListPresenter;

import dagger.Component;

/**
 * Created by booboomx on 17/5/18.
 */
@FragmentScope
@Component(modules = {CategoryModule.class, LiveListModule.class},dependencies = AppComponent.class)
public interface HomeComponent {


    void  inject(HomeFragment homeFragment);
    void injcet(LiveListFragment liveListFragment);


    CategoryPresenter getCategoryPresenter();

    LiveListPresenter getLiveListPresenter();


}
