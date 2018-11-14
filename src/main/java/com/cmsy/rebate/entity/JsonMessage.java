package com.cmsy.rebate.entity;

import com.alibaba.fastjson.JSONArray;

public class JsonMessage {
    private int code;
    private String message;
    private Object data;
    private String payType;

    public JsonMessage() {
        super();
    }

    public JsonMessage(int code, String message, JSONArray data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
