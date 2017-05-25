package com.booboomx.tvshow.mvp.presenter;

import android.util.Log;

import com.booboomx.tvshow.app.App;
import com.booboomx.tvshow.bean.Room;
import com.booboomx.tvshow.bean.RoomLine;
import com.booboomx.tvshow.http.APIRetrofit;
import com.booboomx.tvshow.mvp.base.BasePresenter;
import com.booboomx.tvshow.mvp.view.IRoomView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by booboomx on 17/5/19.
 */

public class RoomPresenter extends BasePresenter<IRoomView> {
    public static final String TAG = RoomPresenter.class.getSimpleName();

    public RoomPresenter(App app) {
        super(app);
    }

    public void enterRoom(String uid) {
        enterRoom(uid, false);
    }


    public void enterRoom(String uid, final boolean isShowing) {

        if (isViewAttached()) {
            getView().showProgress();
        }


        APIRetrofit.getAPIService()
                .enterRoom(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Room>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Log.i(TAG, "onStart: ");
                    }

                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                        if (isViewAttached())
                            getView().onCompleted();

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i(TAG, "onError: " + e.getMessage());
                        if (isViewAttached())
                            getView().onError(e);
                    }

                    @Override
                    public void onNext(Room room) {

                        Log.i(TAG, "onNext: " + room.getTitle());
                        if (isViewAttached())
                            getView().enterRoom(room);

                        if (room != null) {

                            String url = null;
                            RoomLine roomLine = room.getLive().getWs();

                            RoomLine.FlvBean flv = roomLine.getFlv();

                            if (flv != null) {
                                url = flv.getValue(isShowing).getSrc();
                            } else {
                                url = roomLine.getHls().getValue(isShowing).getSrc();
                            }
                            Log.i(TAG, "onNext: url" + url);
                            if (isViewAttached())
                                getView().playUrl(url);

                        }

                    }
                });

    }


}
