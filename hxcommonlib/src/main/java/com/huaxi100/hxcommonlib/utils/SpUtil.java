package com.huaxi100.hxcommonlib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Rhino
 * @version V1.0
 * @Des: TODO
 * @created
 */
@SuppressLint("CommitPrefEdits")
public class SpUtil {

    private SharedPreferences sp = null;

    private SharedPreferences.Editor edit = null;

    public SpUtil(SharedPreferences sp) {
        this.sp = sp;
        edit = sp.edit();
    }

    public SpUtil(Context context, String spName) {
        this(context.getSharedPreferences(spName, Context.MODE_PRIVATE));
    }

    public void setValue(String key, boolean value) {
        edit.putBoolean(key, value);
        edit.commit();
    }

    public void setValue(String key, float value) {
        edit.putFloat(key, value);
        edit.commit();
    }

    public void setValue(String key, int value) {
        edit.putInt(key, value);
        edit.commit();
    }

    public void setValue(String key, long value) {
        edit.putLong(key, value);
        edit.commit();
    }

    public void setValue(String key, Set<String> value) {
        edit.putStringSet(key, value);
        edit.commit();
    }

    public Set<String> getValue(String key) {
        return sp.getStringSet(key, new HashSet<String>());
    }

    public void addSetValue(String key, String value) {
        Set<String> set = getValue(key);
        set.add(value);
        setValue(key, set);
    }

    public void setValue(String key, String value) {
        edit.putString(key, value);
        edit.commit();
    }

    public boolean setHasKey(String key,String value) {
        Set<String> valueSet = getValue(key);
        Iterator<String> it = valueSet.iterator();
        while(it.hasNext()){
            String tempValue=it.next();
            if(tempValue.equals(value)){
                return  true;
            }
        }
        return false;
    }

    public boolean getBoolValue(String key) {
        return sp.getBoolean(key, true);
    }

    public float getFloatValue(String key) {
        return sp.getFloat(key, 0);
    }

    public int getIntegerValue(String key) {
        return sp.getInt(key, 0);
    }

    public long getLongValue(String key) {
        return sp.getLong(key, 0);
    }

    public String getStringValue(String key) {
        return sp.getString(key, "");
    }

    public void remove(String key) {
        edit.remove(key);
        edit.commit();
    }

    public void clear() {
        edit.clear();
        edit.commit();
    }

}