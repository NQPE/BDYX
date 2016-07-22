package com.custom.bdyx;

import com.custom.bdyx.utils.LogUtil;
import com.huaxi100.hxcommonlib.app.AppContext;

/**
 * Created by levi on 2016/7/22.
 */
public class BDYXApplication extends AppContext{

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.init(this);
    }
}
