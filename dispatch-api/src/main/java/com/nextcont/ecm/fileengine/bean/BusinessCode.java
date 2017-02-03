package com.nextcont.ecm.fileengine.bean;

/**
 * 代表业务处理代码
 * Created by wangxudong on 16/1/5.
 */
public enum BusinessCode {

    SUCCESS("200"),
    NOT_FOUND("404"),
    APPLICATION_ERROR("500"),
    DATA_ACCESS_ERROR("501");

    private String code;

    BusinessCode(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
