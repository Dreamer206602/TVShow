package com.booboomx.tvshow.app;

import android.app.Application;
import android.content.Context;

import com.booboomx.tvshow.dagger.component.AppComponent;
import com.booboomx.tvshow.dagger.component.DaggerAppComponent;
import com.booboomx.tvshow.dagger.module.AppModule;
import com.booboomx.tvshow.dao.greendao.DaoMaster;
import com.booboomx.tvshow.dao.greendao.DaoSession;
import com.booboomx.tvshow.http.Constants;

/**
 * Created by booboomx on 17/5/18.
 */

public class App extends Application {
    private static final String BUGLY_ID  = "28aeafeef1";


    private static App instance;

    public static App getInstance(){
        return instance;
    }
    private DaoMaster.DevOpenHelper mHelper;
    private DaoSession mDaoSession;

    private AppComponent mAppComponent;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initDataBase();
        mAppComponent= DaggerAppComponent.builder().appModule(new AppModule(this, Constants.BASE_URL)).build();
    }

    private void initDataBase() {

        mHelper=new DaoMaster.DevOpenHelper(this,"video-db",null);
        DaoMaster daoMaster=new DaoMaster(mHelper.getWritableDatabase());

        mDaoSession=daoMaster.newSession();

    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
