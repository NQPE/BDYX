package com.huaxi100.hxcommonlib.utils;

import com.google.gson.Gson;

/**
 * Created by hx100 on 2016/5/12.
 */
public class JsonUtils {
    /**
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }


    /**
     * @param str
     * @param type
     * @return
     */
    public static <T> T fromJson(String str, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }
}
