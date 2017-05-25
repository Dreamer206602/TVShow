package com.booboomx.tvshow.Ui.activity;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.Ui.fragemnt.FullRoomFragment;
import com.booboomx.tvshow.Ui.fragemnt.RoomFragment;
import com.booboomx.tvshow.base.BaseActivity;
import com.booboomx.tvshow.http.Constants;
import com.booboomx.tvshow.widget.BottomTabView;

import java.util.List;

public class ContentActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_content;
    }
    @Override
    public void initUI() {
        switchFragment(getIntent());
    }

    private void switchFragment(Intent intent) {
        int fragmentKey = intent.getIntExtra(Constants.KEY_FRAGMENT, 0);
        switch (fragmentKey) {
            case Constants.ROOM_FRAGMENT:
                replaceFragment(RoomFragment.newInstance(intent.getStringExtra(Constants.KEY_UID)));
                break;
            case Constants.LIVE_FRAGMENT:
                break;
            case Constants.WEB_FRAGMENT:
                break;
            case Constants.LOGIN_FRAGMENT:
                break;
            case Constants.ABOUT_FRAGMENT:
                break;
            case Constants.FULL_ROOM_FRAGMENT:
                String uid = intent.getStringExtra(Constants.KEY_UID);
                String cover = intent.getStringExtra(Constants.KEY_COVER);
                replaceFragment(FullRoomFragment.newInstance(uid,cover));
                break;
            case Constants.SEARCH_FRAGMENT:
                break;


        }

    }

    @Override
    public void setListener() {

    }


    public void replaceFragment(Fragment fragment) {
        replaceFragment(R.id.container, fragment);
    }


    public void replaceFragment(@IdRes int id, Fragment fragment) {

        getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }


    @Override
    protected List<BottomTabView.TabItemView> getTabViews() {
        return null;
    }

    @Override
    protected List<Fragment> getFragments() {
        return null;
    }
}
