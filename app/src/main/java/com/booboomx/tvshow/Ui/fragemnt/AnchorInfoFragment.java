package com.booboomx.tvshow.Ui.fragemnt;


import android.os.Bundle;
import android.widget.TextView;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.Utils.DecimalFormatUtil;
import com.booboomx.tvshow.base.SimpleFragment;
import com.booboomx.tvshow.bean.Room;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 主播信息界面
 */
public class AnchorInfoFragment extends SimpleFragment {
    public static final String TAG=AnchorInfoFragment.class.getSimpleName();
    @BindView(R.id.civAvatar)
    CircleImageView civAvatar;
    @BindView(R.id.tvAnchorName)
    TextView tvAnchorName;
    @BindView(R.id.tvAccount)
    TextView tvAccount;
    @BindView(R.id.tvFans)
    TextView tvFans;
    @BindView(R.id.tvWeight)
    TextView tvWeight;
    @BindView(R.id.tvStartLiveTime)
    TextView tvStartLiveTime;
    @BindView(R.id.tvAge)
    TextView tvAge;
    @BindView(R.id.tvEmotionalState)
    TextView tvEmotionalState;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @BindView(R.id.tvOccupation)
    TextView tvOccupation;
    private Room mRoom;

    public static AnchorInfoFragment newInstance(Room room) {
        Bundle bundle = new Bundle();
        AnchorInfoFragment fragment = new AnchorInfoFragment();
        fragment.mRoom = room;
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onUpdaeAnchor(Room room) {
        this.mRoom = room;
        initData();

    }


    @Override
    public int getFragmentId() {
        return R.layout.fragment_anchor_info;
    }

    @Override
    public void initUI() {



        if (mRoom != null) {
            Glide.with(this)
                    .load(mRoom.getAvatar())
                    .error(R.drawable.mine_default_avatar)
                    .placeholder(R.drawable.mine_default_avatar)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(civAvatar);
            tvAnchorName.setText(mRoom.getNick());
            tvAccount.setText(String.valueOf(mRoom.getNo()));
            tvFans.setText(String.valueOf(mRoom.getFollow()));
            tvWeight.setText(DecimalFormatUtil.formatW(mRoom.getWeight() / 100));
            tvStartLiveTime.setText(mRoom.getAnnouncement());

        }
    }

    @Override
    public void initData() {



    }

    @Override
    public void setListener() {

    }


}
