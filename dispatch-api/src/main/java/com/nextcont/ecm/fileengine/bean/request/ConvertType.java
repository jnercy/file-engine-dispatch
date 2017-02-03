package com.nextcont.ecm.fileengine.bean.request;


/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/10/20
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */
public enum ConvertType {

    local,cloud;

    private String type;

    ConvertType (String type){
        this.type = type;
    }

    ConvertType() {
    }

    public static ConvertType getConvertType(String type){
        for(ConvertType u : ConvertType.values()){
            if(u.name().equals(type))
                return u;
        }
        return  ConvertType.local;

    }

    public String getType() {
        return type;
    }

}
