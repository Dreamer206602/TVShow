package com.booboomx.tvshow.Ui.fragemnt;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.base.SimpleFragment;
import com.booboomx.tvshow.http.Constants;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 关注的界面
 */
public class FollowFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvLogin)
    TextView tvLogin;

    @Override
    public int getFragmentId() {
        return R.layout.fragment_follow;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.tab_follw);

    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }


    @OnClick({R.id.ivLeft, R.id.ivRight, R.id.tvLogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                startActivity(getFragmentIntent(Constants.SEARCH_FRAGMENT));
                break;
            case R.id.ivRight:
                startLogin();
                break;
            case R.id.tvLogin:
                startLogin();
                break;
        }
    }
}
