package com.huaxi100.hxcommonlib.presenter;

import com.huaxi100.hxcommonlib.view.MvpView;

/**presenter层基类，所有的xxxPresenter需要继承自该类
 * 在初始化时调用attachView(MvpView)
 * Created by hx100 on 2016/5/12.
 */
public class BasePresenter<V extends MvpView> implements IPresenter<V> {

    private V view;

    @Override
    public void attachView(V mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("请求数据前需要调用Presenter.attachView(MvpView)");
        }
    }
}
