package com.booboomx.tvshow.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by booboomx on 17/5/15.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static final int INVALID_VAL = -1;
    private static final int COLOR_DEFAULT = Color.parseColor("#7f000000");


    public abstract  int getLayoutId();
    public abstract  void  initUI();

    private Unbinder mUnBinder;
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 从4.4版本开始
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }

        setContentView(getLayoutId());
        mUnBinder = ButterKnife.bind(this);




    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void  setStatusViewColor(int statusColor){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){

            if(statusColor!=INVALID_VAL){
                getWindow().setStatusBarColor(statusColor);
            }

            return;
        }


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT&& Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){


            int color=COLOR_DEFAULT;

            ViewGroup contentView = (ViewGroup) findViewById(android.R.id.content);

            if(statusColor!=INVALID_VAL){
                color=statusColor;
            }


            View statusBarView=new View(this);
            ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(this));
            statusBarView.setBackgroundColor(color);
            contentView.addView(statusBarView,lp);

        }


    }


    /**
     * 获取状态栏的高度
     * @param context
     * @return
     */
    public int getStatusBarHeight(Context context){

        int result=0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if(resourceId>0){
            result=context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }


    @Override
    protected void onDestroy() {
        mUnBinder.unbind();
        super.onDestroy();
    }
}
