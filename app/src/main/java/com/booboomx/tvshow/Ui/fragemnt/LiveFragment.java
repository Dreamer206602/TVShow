package com.booboomx.tvshow.Ui.fragemnt;


import android.support.v4.app.Fragment;
import android.util.Log;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.base.BaseLazyLoadFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveFragment extends BaseLazyLoadFragment {


    public static final String TAG=LiveFragment.class.getSimpleName();



    @Override
    public int getFragmentId() {
        return R.layout.fragment_live;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {

        Log.i(TAG, "initData: LiveFragment");

    }

    @Override
    public void setListener() {

    }



}
