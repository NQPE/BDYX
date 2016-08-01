package com.custom.bdyx.view;

import com.huaxi100.hxcommonlib.view.IMvpView;

/**
 * Created by levi on 2016/7/22.
 */
public interface IBaseMvpView<T> extends IMvpView {
    /**
     * 加载开始
     */
    void onLoadStart();
    /**
     * 加载结束
     */
    void onLoadEnd();

    /**
     * 加载错误message
     */
    void onLoadError(String message);

    /**
     * 加载返回null数据
     */
    void onLoadNull();

    /**
     * 加载得到数据
     */
    void onLoadData(T data);
}
