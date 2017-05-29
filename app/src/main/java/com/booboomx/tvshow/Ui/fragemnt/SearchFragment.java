package com.booboomx.tvshow.Ui.fragemnt;


import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.booboomx.tvshow.R;
import com.booboomx.tvshow.base.BaseLazyLoadFragment;
import com.booboomx.tvshow.mvp.base.BasePresenter;
import com.booboomx.tvshow.mvp.base.BaseView;
import com.king.base.util.StringUtils;
import com.king.base.util.ToastUtils;

import butterknife.BindView;

/**
 * 搜索的界面
 */
public class SearchFragment extends BaseLazyLoadFragment<BaseView,BasePresenter<BaseView>> {

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.etKey)
    EditText etKey;
    @BindView(R.id.tvRight)
    TextView tvRight;
    private LiveListFragment liveListFragment;
    public  static SearchFragment newInstance() {
        Bundle bundle=new Bundle();
        SearchFragment fragment=new SearchFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public BasePresenter<BaseView> createPresenter() {
        return new BasePresenter<>(getApp());
    }


    @Override
    public int getFragmentId() {
        return R.layout.fragment_search;
    }

    @Override
    public void initUI() {

        etKey.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

               if(event.getAction()==KeyEvent.ACTION_UP){
                   if(keyCode==KeyEvent.KEYCODE_ENTER||keyCode==KeyEvent.KEYCODE_SEARCH){

                       clickSearch();
                       return true;
                   }
               }


                return false;
            }


        });

    }

    private boolean checkInputKey(){
        if(StringUtils.isBlank(etKey.getText())){
            ToastUtils.showToast(getContext(),R.string.tips_search_keywords_cannot_be_empty);
            return false;
        }
        return true;
    }

    /**
     * 隐藏软键盘
     *
     * @param v
     */
    public void hideInputMethod(final EditText v) {

        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),0);
        v.clearFocus();
    }

    private void clickSearch(){
        if(checkInputKey()){
            hideInputMethod(etKey);
            liveListFragment.search(etKey.getText().toString(),0);
        }

    }

    @Override
    public void initData() {

        liveListFragment=LiveListFragment.newInstance(null,true);
        replaceChildFragment(R.id.fragment,liveListFragment);

    }

    @Override
    public void setListener() {

    }


}
