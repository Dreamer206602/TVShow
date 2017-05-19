package com.booboomx.tvshow.Ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.bean.Recommend;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

/**
 * Created by booboomx on 17/5/19.
 */

public class RecommendChildAdapter extends ViewHolderRecyclerAdapter<Recommend.RoomBean.ListBean> {

    public RecommendChildAdapter(Context context, List<Recommend.RoomBean.ListBean> listData) {
        super(context, listData);
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return inflate(R.layout.item_live_list);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, Recommend.RoomBean.ListBean data, int position) {

        ImageView iv = holder.getView(R.id.iv);


        Glide.with(context)
                .load(data.getThumb())
                .placeholder(R.mipmap.live_default)
                .error(R.mipmap.live_default)
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(iv);



        holder.setText(R.id.tvTitle,data.getTitle());
        holder.setText(R.id.tvName,data.getNick());
        holder.setText(R.id.tvViewer,data.getViews());



    }
}
