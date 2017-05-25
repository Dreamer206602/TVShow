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

/**
 *
 */
public class RoomFragment extends BaseLazyLoadFragment<IRoomView,RoomPresenter>implements IRoomView {
    public static final String TAG=RoomFragment.class.getSimpleName();
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
    private List<CharSequence>listTitle;
    private List<Fragment>listData;
    private Room mRoom;

    private AnchorInfoFragment mAnchorInfoFragment;
    private VideoFragment mVideoFragment;
    public static RoomFragment newInstance(String uid) {
        Bundle bundle=new Bundle();
        RoomFragment roomFragment=new RoomFragment();
        roomFragment.uid=uid;
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
        listTitle=new ArrayList<>();
        listData=new ArrayList<>();

        listTitle.add(getString(R.string.room_chat));
        listTitle.add(getString(R.string.room_ranking));
        listTitle.add(getString(R.string.room_anchor));


        listData.add(new ChatFragment());
        listData.add(new RankFragment());
        mAnchorInfoFragment=AnchorInfoFragment.newInstance(mRoom);
        listData.add(mAnchorInfoFragment);


        mPagerFragmentAdapter=new ViewPagerFragmentAdapter(getChildFragmentManager(),listData,listTitle);
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
        this.mRoom=room;
        mAnchorInfoFragment.onUpdaeAnchor(mRoom);
        mPagerFragmentAdapter.notifyDataSetChanged();

    }

    @Override
    public void playUrl(String url) {

        Log.i(TAG, "playUrl: "+url);
        if(mVideoFragment==null){
            mVideoFragment=VideoFragment.newInstance(url,false);
        }

        replaceChildFragment(R.id.frameVideo,mVideoFragment);



    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(isLandScape()){
            llRoomChat.setVisibility(View.GONE);
            ivFullScreen.setVisibility(View.GONE);
        }else{
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

    public boolean isLandScape(){

        return getActivity().getRequestedOrientation()== ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
    }

    public void updateVideoLayoutParams(){

        ViewGroup.LayoutParams lp = videoContent.getLayoutParams();

        if(isLandScape()){
            lp.height = DensityUtil.getDisplayMetrics(getContext()).heightPixels;
        }else{
            lp.height = (int)(DensityUtil.getDisplayMetrics(getContext()).widthPixels / 16.0f * 9.0f);
        }

        videoContent.setLayoutParams(lp);

    }

    public void clickFrameVideo(){

    }

    public void clickBack(){
        if(isLandScape()){
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else{
            finish();
        }
    }

    public void clickShare(){

    }

    public void clickFullScreen(){
        if(isLandScape()){
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else{
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    public void clickFollow(){
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
                clickShare();
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
