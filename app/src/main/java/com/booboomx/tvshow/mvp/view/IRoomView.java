package com.booboomx.tvshow.mvp.view;

import com.booboomx.tvshow.bean.Room;
import com.booboomx.tvshow.mvp.base.BaseView;

/**
 * Created by booboomx on 17/5/19.
 */

public interface IRoomView extends BaseView {

    void enterRoom(Room room);

    void playUrl(String url);
}
