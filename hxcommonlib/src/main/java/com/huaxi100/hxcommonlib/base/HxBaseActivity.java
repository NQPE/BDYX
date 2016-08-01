package com.huaxi100.hxcommonlib.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.huaxi100.hxcommonlib.R;
import com.huaxi100.hxcommonlib.app.AppManager;
import com.huaxi100.hxcommonlib.utils.EventBusCenter;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;

import butterknife.ButterKnife;

/**
 * Created by hx100 on 2016/5/12.
 * 继承RxAppCompatActivity防止RxJava的内存泄漏
 *
 */
public abstract class HxBaseActivity extends RxAppCompatActivity {
    /**
     * Log tag
     */
    protected static String TAG = null;
    protected HxBaseActivity activity;
    /**
     * 加载对话框
     */
    protected ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        activity = this;
        TAG = getClass().getSimpleName();
        if (setLayoutResID() != 0) {
            setContentView(setLayoutResID());
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        //必须放在setContentView之后
        ButterKnife.bind(this);
        //
        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
        doBusiness();
    }

    public void showDialog() {
        if (activity == null) {
            return;
        }
        if (loadingDialog == null) {
            loadingDialog = new ProgressDialog(activity,R.style.LoadingDialog);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        loadingDialog.show();
        View dialogView = makeView(R.layout.view_loading);
//        ImageView imageView = (ImageView) dialogView.findViewById(R.id.rotate_image);
//        imageView.setImageResource(R.drawable.loading_ani);
//        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
//        animationDrawable.start();
        loadingDialog.setContentView(dialogView);
    }

    public void dismissDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
    public View makeView(int resId) {
        LayoutInflater inflater = LayoutInflater.from(this);
        return inflater.inflate(resId, null);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }
        AppManager.getAppManager().finishActivity(this);
    }

    public void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param clazz
     */
    public void skip(Class<? extends Activity> clazz) {
        startActivity(new Intent(this, clazz));
    }

    public void skip(Class<? extends Activity> cls, Serializable... serializ) {
        Intent intent = new Intent(this, cls);
        Bundle extras = new Bundle();
        for (int i = 0; i < serializ.length; i++) {
            Serializable s = serializ[i];
            // 放对象的规则，以顺序为键
            extras.putSerializable(i + "", s);
        }
        intent.putExtras(extras);
        startActivity(intent);
    }

    public Serializable getVo(String key) {
        Intent myIntent = getIntent();
        Bundle bundle = myIntent.getExtras();
        if (bundle == null) {
            return "";
        }
        Serializable vo = bundle.getSerializable(key);
        return vo;
    }

    public void skip(String action) {
        startActivity(new Intent(action));
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
    /**
     * @param
     * @return void
     * @Des: 重写此方法，实现业务
     */
    public abstract void doBusiness();

    /**
     * is bind eventBus
     *
     * @return
     */
    protected abstract boolean isBindEventBusHere();

    /**
     * 设置layout
     * @return
     */
    protected abstract int setLayoutResID();
}
