package com.booboomx.tvshow.Ui.fragemnt;


import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.Utils.DensityUtil;
import com.booboomx.tvshow.base.BaseLazyLoadFragment;
import com.booboomx.tvshow.bean.Room;
import com.booboomx.tvshow.mvp.presenter.RoomPresenter;
import com.booboomx.tvshow.mvp.view.IRoomView;
import com.king.base.adapter.ViewPagerFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 *
 */
public class RoomFragment extends BaseLazyLoadFragment<IRoomView, RoomPresenter> implements IRoomView {
    public static final String TAG = RoomFragment.class.getSimpleName();
    @BindView(R.id.frameVideo)
    FrameLayout frameVideo;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.ivShare)
    ImageView ivShare;
    @BindView(R.id.tvRoomTitle)
    TextView tvRoomTitle;
    @BindView(R.id.ivFullScreen)
    ImageView ivFullScreen;
    @BindView(R.id.rlRoomInfo)
    RelativeLayout rlRoomInfo;
    @BindView(R.id.llRoomChat)
    LinearLayout llRoomChat;

    @BindView(R.id.videoContent)
    RelativeLayout videoContent;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.tvFollow)
    TextView tvFollow;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private String uid;
    private ViewPagerFragmentAdapter mPagerFragmentAdapter;
    private List<CharSequence> listTitle;
    private List<Fragment> listData;
    private Room mRoom;

    private AnchorInfoFragment mAnchorInfoFragment;
    private VideoFragment mVideoFragment;

    public static RoomFragment newInstance(String uid) {
        Bundle bundle = new Bundle();
        RoomFragment roomFragment = new RoomFragment();
        roomFragment.uid = uid;
        roomFragment.setArguments(bundle);
        return roomFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getFragmentId() {
        return R.layout.fragment_room;
    }


    @Override
    public void initUI() {

        Log.i(TAG, "initUI: ");

        updateVideoLayoutParams();
        listTitle = new ArrayList<>();
        listData = new ArrayList<>();

        listTitle.add(getString(R.string.room_chat));
        listTitle.add(getString(R.string.room_ranking));
        listTitle.add(getString(R.string.room_anchor));


        listData.add(new ChatFragment());
        listData.add(new RankFragment());
        mAnchorInfoFragment = AnchorInfoFragment.newInstance(mRoom);
        listData.add(mAnchorInfoFragment);


        mPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), listData, listTitle);
        viewPager.setAdapter(mPagerFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void initData() {

        getPresenter().enterRoom(uid);

    }

    @Override
    public void setListener() {

    }


    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {


    }

    @Override
    public void onError(Throwable e) {


    }

    @Override
    public void enterRoom(Room room) {

        Log.i(TAG, "enterRoom: ");
        this.mRoom = room;
        mAnchorInfoFragment.onUpdaeAnchor(mRoom);
        mPagerFragmentAdapter.notifyDataSetChanged();

    }

    @Override
    public void playUrl(String url) {

        Log.i(TAG, "playUrl: " + url);
        if (mVideoFragment == null) {
            mVideoFragment = VideoFragment.newInstance(url, false);
        }

        replaceChildFragment(R.id.frameVideo, mVideoFragment);


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (isLandScape()) {
            llRoomChat.setVisibility(View.GONE);
            ivFullScreen.setVisibility(View.GONE);
        } else {
            llRoomChat.setVisibility(View.VISIBLE);
            ivFullScreen.setVisibility(View.VISIBLE);
        }
        updateVideoLayoutParams();

    }

    @NonNull
    @Override
    public RoomPresenter createPresenter() {
        return new RoomPresenter(getApp());
    }

    public boolean isLandScape() {

        return getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
    }

    public void updateVideoLayoutParams() {

        ViewGroup.LayoutParams lp = videoContent.getLayoutParams();

        if (isLandScape()) {
            lp.height = DensityUtil.getDisplayMetrics(getContext()).heightPixels;
        } else {
            lp.height = (int) (DensityUtil.getDisplayMetrics(getContext()).widthPixels / 16.0f * 9.0f);
        }

        videoContent.setLayoutParams(lp);

    }

    public void clickFrameVideo() {

    }

    public void clickBack() {
        if (isLandScape()) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            finish();
        }
    }


    public void clickFullScreen() {
        if (isLandScape()) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    private void showShare() {
        int uid = mRoom.getUid();
        int no = mRoom.getNo();
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle(mRoom.getTitle());
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
        oks.setTitleUrl("https://m.quanmin.tv/"+String.valueOf(uid)+"?from=android&uid="+String.valueOf(uid)+"&fuid="+String.valueOf(no)+"&rid="+String.valueOf(uid));
        // text是分享文本，所有平台都需要这个字段
        oks.setText(mRoom.getIntro());
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        oks.setImageUrl(mRoom.getAvatar());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");
        oks.setUrl("https://m.quanmin.tv/"+String.valueOf(uid)+"?from=android&uid="+String.valueOf(uid)+"&fuid="+String.valueOf(no)+"&rid="+String.valueOf(uid));
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(mRoom.getStatus());
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");

        oks.setSiteUrl("https://m.quanmin.tv/"+String.valueOf(uid)+"?from=android&uid="+String.valueOf(uid)+"&fuid="+String.valueOf(no)+"&rid="+String.valueOf(uid));
        // 启动分享GUI
        oks.show(getContext());
    }


    public void clickFollow() {
        startLogin();
    }

    @OnClick({R.id.frameVideo, R.id.ivBack, R.id.ivShare, R.id.ivFullScreen, R.id.videoContent, R.id.tvFollow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.videoContent:
            case R.id.frameVideo:
                clickFrameVideo();
                break;
            case R.id.ivBack:
                clickBack();
                break;
            case R.id.ivShare:
                showShare();
                break;
            case R.id.ivFullScreen:
                clickFullScreen();
                break;
            case R.id.tvFollow:
                clickFollow();
                break;
        }
    }


}
