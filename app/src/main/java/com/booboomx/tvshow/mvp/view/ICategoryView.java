package com.booboomx.tvshow.mvp.view;

import com.booboomx.tvshow.bean.LiveCategory;
import com.booboomx.tvshow.mvp.base.BaseView;

import java.util.List;

/**
 * Created by booboomx on 17/5/18.
 */

public interface ICategoryView extends BaseView{

    void  onGetLiveCategory(List<LiveCategory>list);
}
