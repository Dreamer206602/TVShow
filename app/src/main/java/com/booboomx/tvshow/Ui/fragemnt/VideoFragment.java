package com.booboomx.tvshow.Ui.fragemnt;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.base.SimpleFragment;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.pili.pldroid.player.widget.PLVideoView;

import butterknife.BindView;

/**
 *
 */
public class VideoFragment extends SimpleFragment {
    public static final String TAG=VideoFragment.class.getSimpleName();
    @BindView(R.id.ptv)
    PLVideoTextureView vtv;
    private int mRotation;
    private String url;
    private boolean isFull;
    public static VideoFragment newInstance(String url,boolean isFull) {
        Bundle args = new Bundle();
        VideoFragment fragment = new VideoFragment();
        fragment.url = url;
        fragment.isFull = isFull;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getFragmentId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initUI() {

    }


    public void start(){
        if (vtv != null) {
            vtv.start();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        start();
    }

    @Override
    public void onPause() {
        super.onPause();
        pause();
    }

    private void pause() {
        if (vtv != null) {
            vtv.pause();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPlayBack();
    }

    private void stopPlayBack() {

        if (vtv != null) {

            vtv.stopPlayback();
        }
    }


    @Override
    public void initData() {
        Log.i(TAG, "initUI:sss "+url);
        vtv.setVideoPath(url);
        if(isFull){
            vtv.setDisplayOrientation(PLVideoView.ASPECT_RATIO_PAVED_PARENT);
        }else{
            vtv.setDisplayOrientation(PLVideoView.ASPECT_RATIO_16_9);
        }


        vtv.setOnPreparedListener(new PLMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(PLMediaPlayer plMediaPlayer) {
                start();
            }
        });

        vtv.setOnBufferingUpdateListener(new PLMediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(PLMediaPlayer plMediaPlayer, int i) {
                if(i>0){

                }
            }
        });

        vtv.setOnCompletionListener(new PLMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(PLMediaPlayer plMediaPlayer) {

            }
        });

        vtv.setOnInfoListener(new PLMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(PLMediaPlayer plMediaPlayer, int i, int i1) {
                return false;
            }
        });

        vtv.setOnErrorListener(new PLMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(PLMediaPlayer plMediaPlayer, int i) {
                return false;
            }
        });


    }


    public void onClickRotate(View v) {
        mRotation = (vtv.getDisplayAspectRatio() + 90) % 360;
        setDisplayAspectRatio(mRotation);
    }

    /**
     *
     * @param ratio
     *      PLVideoView.ASPECT_RATIO_ORIGIN
     *      PLVideoView.ASPECT_RATIO_FIT_PARENT
     *      PLVideoView.ASPECT_RATIO_PAVED_PARENT
     *      PLVideoView.ASPECT_RATIO_16_9
     *      PLVideoView.ASPECT_RATIO_4_3
     *
     */
    public void setDisplayAspectRatio(int ratio){
        vtv.setDisplayAspectRatio(ratio);
    }


    @Override
    public void setListener() {

    }



}
