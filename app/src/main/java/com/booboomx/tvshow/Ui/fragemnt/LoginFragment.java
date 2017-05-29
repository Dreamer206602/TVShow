package com.booboomx.tvshow.Ui.fragemnt;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.base.SimpleFragment;
import com.king.base.util.ToastUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * 登陆的界面
 */
public class LoginFragment extends SimpleFragment {
    public static final String TAG=LoginFragment.class.getSimpleName();
    @BindView(R.id.ivLeft)
    ImageView mIvLeft;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.tvRight)
    TextView mTvRight;
    @BindView(R.id.etUsername)
    EditText mEtUsername;
    @BindView(R.id.etPassword)
    EditText mEtPassword;
    @BindView(R.id.btnLogin)
    Button mBtnLogin;
    @BindView(R.id.tvForgetPwd)
    TextView mTvForgetPwd;
    @BindView(R.id.ivQQ)
    ImageView mIvQQ;
    @BindView(R.id.ivSina)
    ImageView mIvSina;
    @BindView(R.id.ivWeixin)
    ImageView mIvWeixin;

    @Override
    public int getFragmentId() {
        return R.layout.fragment_login;
    }

    @Override
    public void initUI() {

        mTvTitle.setText(getString(R.string.login));

    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }


    @OnClick({R.id.ivLeft, R.id.ivQQ, R.id.ivWeixin, R.id.ivSina})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;

            case R.id.ivQQ:
                thirdLogin(QQ.NAME);
                break;
            case R.id.ivWeixin:
                thirdLogin(Wechat.NAME);
                break;
            case R.id.ivSina:
                thirdLogin(SinaWeibo.NAME);
                break;


        }
    }


    public void thirdLogin(String platName) {
        Platform platform = ShareSDK.getPlatform(platName);
        //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        platform.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                ToastUtils.showToast(getContext(),arg2.getMessage());
                Log.i(TAG, "onError: "+arg2.getMessage());

            }

            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                // TODO Auto-generated method stub
                //输出所有授权信息
                ToastUtils.showToast(getContext(),arg0.getDb().exportData());
                Log.i(TAG, "onComplete: "+arg0.getDb().exportData());
            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub


            }
        });
        //authorize与showUser单独调用一个即可
        platform.authorize();//单独授权,OnComplete返回的hashmap是空的
        platform.showUser(null);//授权并获取用户信息
        //移除授权
        //weibo.removeAccount(true);
    }


}
