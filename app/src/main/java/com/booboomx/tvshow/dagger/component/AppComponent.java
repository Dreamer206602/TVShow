package com.booboomx.tvshow.dagger.component;

import android.content.Context;

import com.booboomx.tvshow.app.App;
import com.booboomx.tvshow.dagger.module.AppModule;
import com.booboomx.tvshow.http.APIService;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by booboomx on 17/5/18.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void  inject(App app);
    Context getContext();
    Retrofit getRetrofit();

    OkHttpClient getOkHttpClient();

    APIService getAPIService();


}
