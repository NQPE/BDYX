package com.huaxi100.hxcommonlib.base;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huaxi100.hxcommonlib.utils.EventBusCenter;
import com.trello.rxlifecycle.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

/**
 * Created by hx100 on 2016/5/12.
 * 继承RxFragment防止RxJava的内存泄漏
 *
 */
public abstract class HxBaseFragment extends RxFragment {
    /**
     * Log tag
     */
    protected static String TAG = null;

    protected HxBaseActivity activity;

    protected View rootView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            activity = (HxBaseActivity) getActivity();
        } catch (ClassCastException ex) {
            throw new ClassCastException(getActivity().getClass()
                    .getSimpleName() + "not BaseActivity");
        }
        TAG = this.getClass().getSimpleName();
        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        rootView = view;
        doBusiness(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (setLayoutResID() != 0) {
            return inflater.inflate(setLayoutResID(), null);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }
    }
    @Subscribe
    public void onEventMainThread(EventBusCenter eventBusCenter) {
        if (null != eventBusCenter) {
            onEventBusMainThread(eventBusCenter);
        }
    }
    /**
     * when event comming
     *
     * @param eventBusCenter
     */
    protected abstract void onEventBusMainThread(EventBusCenter eventBusCenter);
    @Override
    public void onDetach() {
        super.onDetach();
        // for bug ---> java.lang.IllegalStateException: Activity has been destroyed
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * get the support fragment manager
     *
     * @return
     */
    protected FragmentManager getSupportFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    /**
     * is bind eventBus
     *
     * @return
     */
    protected abstract boolean isBindEventBusHere();

    /**
     * @param
     * @return void
     * @Des: 重写此方法，实现业务
     */
    public abstract void doBusiness(View view);

    /**
     * 设置layout
     *
     * @return
     */
    protected abstract int setLayoutResID();
}
