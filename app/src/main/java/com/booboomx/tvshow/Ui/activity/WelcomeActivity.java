package com.booboomx.tvshow.Ui.activity;

import android.view.animation.Animation;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.Utils.JumpUtils;
import com.king.base.SplashActivity;

/**
 * 欢迎页
 */
public class WelcomeActivity extends SplashActivity {


    @Override
    public int getContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    public Animation.AnimationListener getAnimationListener() {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                JumpUtils.go2Main(WelcomeActivity.this);
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }
}
