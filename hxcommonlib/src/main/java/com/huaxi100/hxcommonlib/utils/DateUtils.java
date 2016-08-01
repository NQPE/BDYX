package com.huaxi100.hxcommonlib.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理相关工具类
 * Created by hx100 on 2016/5/12.
 */
public class DateUtils {
    @SuppressLint("SimpleDateFormat")
    public static String formatTime(long time, String format) {
        return new SimpleDateFormat(format).format(new Date(time));
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatTime(Date time, String format) {
        // yyyy-MM-dd HH:mm:ss
        return new SimpleDateFormat(format).format(time);
    }

    /**
     * 格式化 yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static String formatTime(String time) {
        if (TextUtils.isEmpty(time)) {
            return "传入时间为空";
        }
        return formatTime(Long.parseLong(time), "yyyy-MM-dd HH:mm");
    }

    public static String formatTime(String time,String modle) {
        if (TextUtils.isEmpty(time)) {
            return "传入时间为空";
        }
        return formatTime(Long.parseLong(time), modle);
    }

    public static String formatHour(String time) {
        if (TextUtils.isEmpty(time)) {
            return "传入时间为空";
        }
        return formatTime(Long.parseLong(time), "HH:mm");
    }

}
