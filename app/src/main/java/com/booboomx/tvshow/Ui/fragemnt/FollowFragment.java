package com.booboomx.tvshow.Ui.fragemnt;


import android.support.v4.app.Fragment;
import android.util.Log;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.base.BaseLazyLoadFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowFragment extends BaseLazyLoadFragment {

    public static final String TAG=FollowFragment.class.getSimpleName();



    @Override
    public int getFragmentId() {
        return R.layout.fragment_follow;
    }

    @Override
    public void initUI() {


    }

    @Override
    public void initData() {
        Log.d(TAG, "initData: FollowFragment");

    }

    @Override
    public void setListener() {

    }



}
