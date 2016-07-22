package com.huaxi100.hxcommonlib.utils;

import java.io.File;
import java.util.List;

/**
 */
public class Utils {
    public static boolean isEmpty(List<?> list) {
        return (list == null || list.size() == 0);
    }

    public static boolean isEmpty(File file) {
        return file == null;
    }

    public static <T> boolean isEmpty(T[] array) {
        return ((array == null) || (array.length) == 0);
    }

    public static boolean isEmpty(String val) {
        if (val == null || val.matches("\\s") || val.length() == 0
                || "null".equalsIgnoreCase(val)) {
            return true;
        }
        return false;
    }
}
