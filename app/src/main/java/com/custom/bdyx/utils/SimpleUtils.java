package com.custom.bdyx.utils;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.custom.bdyx.BDYXApplication;

/**
 * Created by levi on 2016/7/26.
 */
public class SimpleUtils {

    /**
     * 再次包装Glide类 方便后面添加默认图片之类的修改
     *
     * @param url
     * @param iv
     * @param config   配置项 可以根据传入的值来设置不同的默认图片或者其他什么的
     */
    public static void showGlideView(String url, ImageView iv, int config) {
        Glide.with(BDYXApplication.getAppContext()).load(url).centerCrop().into(iv);
    }
}
