package com.nextcont.ecm.fileengine.bean;

/**
 * 表示fastdfs的异常
 * Created by wangxudong on 16/1/19.
 */
public class DfsException extends RuntimeException {

    public DfsException(String msg){super(msg);}
    public DfsException(String msg, Throwable t){super(msg,t);}

}
