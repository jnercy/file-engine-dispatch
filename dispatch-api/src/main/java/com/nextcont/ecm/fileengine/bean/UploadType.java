package com.nextcont.ecm.fileengine.bean;


/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/26
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
public enum UploadType {
    http,
    ftp,
    dfs,
    unknown;

    private String type;


    UploadType (String type){
        this.type = type;
    }

    UploadType() {
    }

    public static  UploadType getUploadType(String type){
        for(UploadType u : UploadType.values()){
            if(u.name().equals(type))
                return u;
        }
        return  UploadType.unknown;

    }

    public String getType() {
        return type;
    }

}
