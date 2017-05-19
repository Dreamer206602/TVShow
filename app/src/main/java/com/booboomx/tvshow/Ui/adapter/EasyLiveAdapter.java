package com.booboomx.tvshow.Ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.bean.LiveInfo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by booboomx on 17/5/19.
 */

public class EasyLiveAdapter extends RecyclerArrayAdapter<LiveInfo> {

    private boolean isShowStatus;

    public EasyLiveAdapter(Context context, List<LiveInfo> objects,boolean isShowStatus) {
        super(context, objects);
        this.isShowStatus=isShowStatus;
    }
    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new LiveViewHolder(parent);
    }

    private class  LiveViewHolder extends BaseViewHolder<LiveInfo>{

        private ImageView iv;
        private TextView tvTitle,tvStatus,tvName,tvViewer;
        public LiveViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_live_list);


            iv=$(R.id.iv);
            tvTitle=$(R.id.tvTitle);
            tvStatus=$(R.id.tvStatus);
            tvName=$(R.id.tvName);
            tvViewer=$(R.id.tvViewer);
        }

        @Override
        public void setData(LiveInfo data) {
            super.setData(data);


            Glide.with(getContext()).load(data.getThumb()).placeholder(R.mipmap.live_default).error(R.mipmap.live_default).crossFade().centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);

            tvTitle.setText(data.getTitle());
            tvName.setText(data.getNick());
            tvViewer.setText(data.getViews());


            if(isShowStatus){
                if(data.getPlay_status()){
                    tvStatus.setVisibility(View.VISIBLE);
                }else{
                    tvStatus.setVisibility(View.GONE);
                }
            }
        }
    }
}
