package com.huaxi100.hxcommonlib.presenter;

import com.huaxi100.hxcommonlib.view.MvpView;

/**
 * presenter基类接口，绑定实现MvpView视图的Activity
 * Created by hx100 on 2016/5/12.
 */
public interface IPresenter<V extends MvpView> {

    /**
     * 绑定view
     *
     * @param mvpView
     */
    void attachView(V mvpView);

    /**
     * 分离view
     */
    void detachView();
}
