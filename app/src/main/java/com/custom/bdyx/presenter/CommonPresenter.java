package com.custom.bdyx.presenter;

import com.custom.bdyx.constants.UrlConstants;
import com.custom.bdyx.model.bean.ResultVo;
import com.custom.bdyx.ui.activity.BaseActivity;
import com.huaxi100.hxcommonlib.base.HxBaseActivity;
import com.huaxi100.hxcommonlib.presenter.BasePresenter;
import com.huaxi100.hxcommonlib.utils.SpUtil;
import com.huaxi100.hxcommonlib.view.IMvpView;

import java.net.UnknownHostException;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Administrator on 2016/5/30.
 */
public class CommonPresenter<V extends IMvpView> extends BasePresenter<V> {

    protected Subscription mSubscription;
    protected HxBaseActivity activity;
    protected SpUtil sp;

    public CommonPresenter(HxBaseActivity activity) {
        this.activity = activity;
        this.sp = new SpUtil(activity, UrlConstants.SP_NAME);
    }

//    protected <T, I extends ISimpleLoadView<T>> Subscriber simpleLoadSubscriber(final I view, final IResultCallback<T> callback) {
//        return new Subscriber<ResultVo<T>>() {
//            @Override
//            public void onCompleted() {
//                view.onLoadEnd();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                if (e instanceof UnknownHostException) {
//                    view.onLoadError("请检查网络");
//                }
//            }
//
//            @Override
//            public void onNext(ResultVo<T> vo) {
//                if (vo.isSucceed()) {
//                    callback.resultSuccess(vo, vo.getData());
//                } else {
//                    callback.resultError(vo);
//                    if (vo.isLogin()) {
//                        SimpleUtils.showLoginAct(activity);
//                    } else {
//                        view.onLoadError(vo.getMessage());
//                    }
//                }
//            }
//
//            @Override
//            public void onStart() {
//                super.onStart();
//                view.onLoadStart();
//            }
//        };
//    }
//
//    protected <T, I extends IRecyclerListView<T>> Subscriber listLoadSubscriber(final I view, final int page, final IResultCallback<List<T>> callback) {
//        return new Subscriber<ResultVo<List<T>>>() {
//            @Override
//            public void onCompleted() {
//                view.onLoadEnd();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                if (e instanceof UnknownHostException) {
//                    view.onLoadError("请检查网络");
//                }
//            }
//
//            @Override
//            public void onNext(ResultVo<List<T>> vo) {
//                if (vo.isSucceed()) {
//                    callback.resultSuccess(vo, vo.getData());
//                } else {
//                    callback.resultError(vo);
//                    if (vo.isLogin()) {
//                        SimpleUtils.showLoginAct(activity);
//                    } else {
//                        view.onLoadError(vo.getMessage());
//                    }
//                }
//            }
//
//            @Override
//            public void onStart() {
//                super.onStart();
//                view.onLoadStart();
//            }
//        };
//    }
//
//    public interface IResultCallback<T> {
//        void resultSuccess(ResultVo<T> vo, T data);
//
//        void resultError(ResultVo<T> vo);
//    }
//
//    protected <T, I extends IRecyclerListView<T>> void handListData(final I view, final int page, ResultVo<List<T>> vo) {
//        List<T> dataList = vo.getData();
//        if (page == 1) {
//            if (Utils.isEmpty(dataList)) {
//                view.onLoadNull();
//            } else {
//                view.refreshData(page, dataList);
//            }
//        } else {
//            if (!Utils.isEmpty(dataList)) {
//                view.loadMoreData(page, dataList);
//                if (dataList.size() < 10) {
//                    view.getNoMoreData();
//                }
//            } else {
//                view.getNoMoreData();
//            }
//        }
//    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null && !mSubscription.isUnsubscribed()) mSubscription.unsubscribe();
    }
}
