package com.huaxi100.hxcommonlib.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.huaxi100.hxcommonlib.app.AppManager;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

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
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
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
