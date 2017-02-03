package com.nextcont.ecm.fileengine.bean.request;

import com.nextcont.ecm.fileengine.bean.UploadType;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/9
 * Time: 9:53
 * To change this template use File | Settings | File Templates.
 */
public enum CallbackType {
    http,
    websocket,
    unknown;

    private String type;


    CallbackType (String type){
        this.type = type;
    }

    CallbackType() {
    }

    public static CallbackType getCallbackType(String type){
        for(CallbackType u : CallbackType.values()){
            if(u.name().equals(type))
                return u;
        }
        return  CallbackType.unknown;

    }

    public String getType() {
        return type;
    }
}
