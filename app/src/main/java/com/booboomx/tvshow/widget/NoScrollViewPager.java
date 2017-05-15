package com.booboomx.tvshow.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by booboomx on 17/5/15.
 */

public class NoScrollViewPager extends ViewPager {

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    public  boolean noScroll=false;

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(noScroll)
            return false;
        else
        return super.onTouchEvent(ev);
    }


    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }
}
