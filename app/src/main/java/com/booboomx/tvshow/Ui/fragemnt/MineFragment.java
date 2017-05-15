package com.booboomx.tvshow.Ui.fragemnt;


import android.support.v4.app.Fragment;
import android.util.Log;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.base.BaseLazyLoadFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseLazyLoadFragment {

    @Override
    public int getFragmentId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {

        Log.i(TAG, "initData: MineFragment");


    }

    @Override
    public void setListener() {

    }


}
