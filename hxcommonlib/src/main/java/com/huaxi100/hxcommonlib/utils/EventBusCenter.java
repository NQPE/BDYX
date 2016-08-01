package com.huaxi100.hxcommonlib.utils;

/**
 * Created by levi on 2016/7/27.
 */
public class EventBusCenter<T> {

    /**
     * reserved data
     */
    private T data;

    /**
     * this code distinguish between different events
     */
    private int eventCode = -1;

    private String eventFlag="";

    public EventBusCenter(int eventCode) {
        this(eventCode, null);
    }
    public EventBusCenter(String eventFlag) {
        this(eventFlag, null);
    }
    public EventBusCenter(int eventCode, T data) {
        this.eventCode = eventCode;
        this.data = data;
    }

    public EventBusCenter(String eventFlag, T data) {
        this.eventFlag = eventFlag;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getEventCode() {
        return eventCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventFlag() {
        return eventFlag;
    }

    public void setEventFlag(String eventFlag) {
        this.eventFlag = eventFlag;
    }
}
