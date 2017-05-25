package com.booboomx.tvshow.http;

import com.booboomx.tvshow.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by booboomx on 17/5/18.
 */
public class APIRetrofit {
    /**
     *  默认超时时间 单位/秒
     */
    private static final int DEFAULT_TIME_OUT = 10;

    private static Retrofit sRetrofit;

    private static OkHttpClient sOKHttpClient;

    public static APIService getAPIService(){
        return getInstance().create(APIService.class);
    }

    public static Retrofit getInstance(){
        if(sRetrofit== null){
            synchronized (APIRetrofit.class){
                if(sRetrofit == null){

                    sRetrofit = new Retrofit.Builder()
                            .baseUrl(Constants.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(getsOKHttpClient())
                            .build();



                }
            }
        }

        return sRetrofit;
    }


    public static OkHttpClient getsOKHttpClient(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if(BuildConfig.DEBUG){
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }else{
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.addInterceptor(logging);

        if(sOKHttpClient == null){
            synchronized (APIRetrofit.class){

                if(sOKHttpClient == null){

                    builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
                    builder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
                    builder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
                    sOKHttpClient=builder.build();

                }
            }
        }

        return sOKHttpClient;
    }






}
