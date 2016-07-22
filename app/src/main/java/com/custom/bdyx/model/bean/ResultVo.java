package com.custom.bdyx.model.bean;

import java.io.Serializable;

/**
 * @Des: 服务端返回数据
 */
public class ResultVo<T> implements Serializable {

    private String charset;

    private String message;

    private int error_code;

    /**
     * 消息数
     */
    private int newprompt;

    /**
     * 首页头部搜索栏中的热词
     */
    private String index_keywords;

    private T data;

    public String getIndex_keywords() {
        return index_keywords;
    }

    public void setIndex_keywords(String index_keywords) {
        this.index_keywords = index_keywords;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public boolean isSucceed() {
        return 0 == error_code;
    }

    public boolean isLogin() {
        return 10 == error_code;
    }

    public int getNewprompt() {
        return newprompt;
    }

    public void setNewprompt(int newprompt) {
        this.newprompt = newprompt;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
