package com.huaxi100.hxcommonlib.app;

import android.app.Application;

/**
 * Created by hx100 on 2016/5/12.
 */
public class AppContext extends Application{
    private static AppContext mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
    }

    public static AppContext getAppContext() {
        return mContext;
    }
}
