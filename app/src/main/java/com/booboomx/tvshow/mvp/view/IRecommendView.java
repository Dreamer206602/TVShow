package com.booboomx.tvshow.mvp.view;

import com.booboomx.tvshow.bean.Banner;
import com.booboomx.tvshow.bean.Recommend;
import com.booboomx.tvshow.mvp.base.BaseView;

import java.util.List;

/**
 * Created by booboomx on 17/5/18.
 */

public interface IRecommendView extends BaseView {


    void onGetRecommend(Recommend recommend);

    void onGetRooms(List<Recommend.RoomBean> list);

    void onGetBanner(List<Banner> list);
}
