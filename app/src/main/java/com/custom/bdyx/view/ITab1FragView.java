package com.custom.bdyx.view;

/**
 * Created by levi on 2016/7/26.
 */
public interface ITab1FragView<T> extends IBaseMvpView<T>{
    /**
     * 加载得到List数据
     */
    void onLoadListData(T data,int page);
}
