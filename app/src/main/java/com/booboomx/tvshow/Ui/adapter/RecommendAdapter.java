package com.booboomx.tvshow.Ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.Utils.DensityUtil;
import com.booboomx.tvshow.bean.Recommend;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.king.base.adapter.HolderRecyclerAdapter;

import java.util.List;

/**
 * Created by booboomx on 17/5/19.
 */

public class RecommendAdapter extends RecyclerArrayAdapter<Recommend.RoomBean>{


    public RecommendAdapter(Context context, List<Recommend.RoomBean> list) {
        super(context, list);

    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }





    public class  RecommendViewHolder extends BaseViewHolder<Recommend.RoomBean>{

        private ImageView iv;
        private TextView tvCategory;
        private TextView tvMore;
        private EasyRecyclerView mRecyclerView;

        RecommendChildAdapter mChildAdapter;
        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_recommend_list);

            iv=$(R.id.iv);
            tvCategory=$(R.id.tvCategroy);
            tvMore=$(R.id.tvMore);
            mRecyclerView=$(R.id.recyclerView);


            SpaceDecoration spaceDecoration=new SpaceDecoration(DensityUtil.dp2px(getContext(),6));


            mRecyclerView.addItemDecoration(spaceDecoration);



            mChildAdapter=new RecommendChildAdapter(getContext(),null);


            GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);

            mRecyclerView.setLayoutManager(gridLayoutManager);

            mRecyclerView.setAdapter(mChildAdapter);

            mChildAdapter.setOnItemClicklistener(new HolderRecyclerAdapter.OnItemClicklistener() {
                @Override
                public void onItemClick(View v, int position) {

                }
            });


        }

        @Override
        public void setData(Recommend.RoomBean data) {
            super.setData(data);

            Glide.with(getContext())
                    .load(data.getIcon())
                    .error(R.drawable.default_recommend_icon)
                    .crossFade()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv);


            tvCategory.setText(data.getName());

            tvMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            mChildAdapter.setListData(data.getList());
            mChildAdapter.notifyDataSetChanged();




        }
    }




}
