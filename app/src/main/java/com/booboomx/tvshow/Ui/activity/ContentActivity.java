package com.booboomx.tvshow.Ui.activity;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.Ui.fragemnt.AboutFragment;
import com.booboomx.tvshow.Ui.fragemnt.FullRoomFragment;
import com.booboomx.tvshow.Ui.fragemnt.LiveFragment;
import com.booboomx.tvshow.Ui.fragemnt.LoginFragment;
import com.booboomx.tvshow.Ui.fragemnt.RoomFragment;
import com.booboomx.tvshow.Ui.fragemnt.SearchFragment;
import com.booboomx.tvshow.Ui.fragemnt.WebFragment;
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
                String title = intent.getStringExtra(Constants.KEY_TITLE);
                String slug = intent.getStringExtra(Constants.KEY_SLUG);
                boolean isTabLive = intent.getBooleanExtra(Constants.KEY_IS_TAB_LIVE, false);
                replaceFragment(LiveFragment.newInstance(title, slug, isTabLive));
                break;
            case Constants.WEB_FRAGMENT:
                String web_title = intent.getStringExtra(Constants.KEY_TITLE);
                String url = intent.getStringExtra(Constants.KEY_URL);
                replaceFragment(WebFragment.newInstance(url, web_title));
                break;
            case Constants.LOGIN_FRAGMENT:
                replaceFragment(new LoginFragment());
                break;
            case Constants.ABOUT_FRAGMENT:
                replaceFragment(new AboutFragment());
                break;
            case Constants.FULL_ROOM_FRAGMENT:
                String uid = intent.getStringExtra(Constants.KEY_UID);
                String cover = intent.getStringExtra(Constants.KEY_COVER);
                replaceFragment(FullRoomFragment.newInstance(uid,cover));
                break;
            case Constants.SEARCH_FRAGMENT:
                replaceFragment(SearchFragment.newInstance());
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
