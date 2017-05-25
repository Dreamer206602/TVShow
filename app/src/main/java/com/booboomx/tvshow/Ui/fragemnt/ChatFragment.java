package com.booboomx.tvshow.Ui.fragemnt;


import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.base.SimpleFragment;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 聊天的界面
 */
public class ChatFragment extends SimpleFragment {

    @BindView(R.id.tvTips)
    TextView mTvTips;
    @BindView(R.id.ivEmoji)
    ImageView mIvEmoji;
    @BindView(R.id.etChat)
    EditText mEtChat;
    @BindView(R.id.ivDanmu)
    ImageView mIvDanmu;
    @BindView(R.id.ivGif)
    ImageView mIvGif;
    Unbinder unbinder;

    @Override
    public int getFragmentId() {
        return R.layout.fragment_chat;
    }

    @Override
    public void initUI() {


        SpannableStringBuilder ssb=new SpannableStringBuilder();
        //系统通知图片
        ImageSpan imageSpan=new ImageSpan(getContext(),R.drawable.img_dm_xttz);

        SpannableString spannableString=new SpannableString("tips");
        spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.append(spannableString);

        //系统通知内容
        ssb.append(getString(R.string.tips_notice_desc));
        mTvTips.setText(ssb);



    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }



}
