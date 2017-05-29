package com.booboomx.tvshow.Ui.fragemnt;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.base.SimpleFragment;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 关于的界面
 */
public class AboutFragment extends SimpleFragment {

    @BindView(R.id.ivLeft)
    ImageView mIvLeft;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.ivRight)
    ImageView mIvRight;
    @BindView(R.id.tvVersion)
    TextView mTvVersion;
    @BindView(R.id.tvAuthor)
    TextView mTvAuthor;
    @BindView(R.id.tvEmail)
    TextView mTvEmail;
    @BindView(R.id.tvCSDN)
    TextView mTvCSDN;
    @BindView(R.id.tvGithub)
    TextView mTvGithub;
    Unbinder unbinder;

    @Override
    public int getFragmentId() {
        return R.layout.fragment_about;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {

        mTvTitle.setText(getString(R.string.about));

    }

    @Override
    public void setListener() {

    }


    @OnClick(R.id.ivLeft)
    public void onClick(View view){
        finish();
    }


}
