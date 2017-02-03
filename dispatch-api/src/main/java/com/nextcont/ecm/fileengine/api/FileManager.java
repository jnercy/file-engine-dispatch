package com.nextcont.ecm.fileengine.api;



import java.io.File;


/**
 * 静态文件管理接口
 * Created by wangxudong on 16/1/18.
 */
public interface FileManager {

    String upload(String fileName,String fullPath,File file);

}
