package com.booboomx.tvshow.mvp.view;

import com.booboomx.tvshow.bean.LiveInfo;
import com.booboomx.tvshow.mvp.base.BaseView;

import java.util.List;

/**
 * Created by booboomx on 17/5/19.
 */

public interface ILiveListView extends BaseView{

    void  onGetLiveList(List<LiveInfo>list);
    void  onGetMoreLiveList(List<LiveInfo>list);



}
