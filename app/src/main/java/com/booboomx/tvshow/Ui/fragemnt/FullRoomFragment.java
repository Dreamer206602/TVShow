package com.booboomx.tvshow.Ui.fragemnt;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.base.BaseLazyLoadFragment;
import com.booboomx.tvshow.bean.Room;
import com.booboomx.tvshow.mvp.presenter.RoomPresenter;
import com.booboomx.tvshow.mvp.view.IRoomView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.king.view.flutteringlayout.FlutteringLayout;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * 全屏的界面
 */
public class FullRoomFragment extends BaseLazyLoadFragment<IRoomView,RoomPresenter>implements IRoomView {
    @BindView(R.id.rlAnchorInfo)
    View rlAnchorInfo;
    @BindView(R.id.civAvatar)
    ImageView civAvatar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvFans)
    TextView tvFans;
    @BindView(R.id.ivCover)
    ImageView ivCover;
    @BindView(R.id.frameVideo)
    FrameLayout frameVideo;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvAccount)
    TextView tvAccount;
    @BindView(R.id.ivInput)
    ImageView ivInput;
    @BindView(R.id.ivFollow)
    ImageView ivFollow;
    @BindView(R.id.ivGift)
    ImageView ivGift;
    @BindView(R.id.ivShare)
    ImageView ivShare;
    @BindView(R.id.ivMessage)
    ImageView ivMessage;
    @BindView(R.id.rlRoomInfo)
    LinearLayout rlRoomInfo;
    @BindView(R.id.videoContent)
    RelativeLayout videoContent;

    @BindView(R.id.flutteringLayout)
    FlutteringLayout flutteringLayout;
    @BindView(R.id.btnHeart)
    Button btnHeart;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private String uid;

    private String coverUrl;

    private VideoFragment videoFragment;

    public static FullRoomFragment newInstance(String uid,String coverUrl) {

        Bundle args = new Bundle();

        FullRoomFragment fragment = new FullRoomFragment();
        fragment.uid = uid;
        fragment.coverUrl = coverUrl;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getFragmentId() {
        return R.layout.fragment_full_room;
    }

    @Override
    public void initUI() {

        tvAccount.setText(String.format(getString(R.string.qm_account),uid));
        Glide.with(this)
                .load(coverUrl)
                .centerCrop()
                .bitmapTransform(new BlurTransformation(getContext(),18,3))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(ivCover);

    }

    @Override
    public void initData() {

        getPresenter().enterRoom(uid);

    }

    @Override
    public void setListener() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void onCompleted() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(Throwable e) {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void enterRoom(Room room) {
        updateAnchorInfo(room);

    }

    @Override
    public void playUrl(String url) {
        if (videoFragment == null) {
            videoFragment = VideoFragment.newInstance(url,true);
        }
        replaceChildFragment(R.id.frameVideo, videoFragment);
        clickHeart();

    }


    private void updateAnchorInfo(Room room){
        if(room!=null){
            rlAnchorInfo.setVisibility(View.VISIBLE);
            Glide.with(this).load(room.getAvatar()).placeholder(R.drawable.mine_default_avatar).error(R.drawable.mine_default_avatar).centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(civAvatar);
            tvName.setText(room.getNick());
            tvFans.setText(String.format(getString(R.string.fans_num),room.getFollow()));

        }
    }

    private void clickHeart() {


    }

    @NonNull
    @Override
    public RoomPresenter createPresenter() {
        return new RoomPresenter(getApp());
    }


    @OnClick({R.id.ivBack,R.id.ivGift})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ivBack:
                finish();
                break;
            case R.id.ivGift:
                flutteringLayout.addHeart();
                break;
        }
    }
}
