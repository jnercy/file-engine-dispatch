package com.nextcont.ecm.fileengine.bean.request;


import com.nextcont.ecm.fileengine.bean.CanUseHttpConnect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

/**
 * 上传文件
 * Created by wangxudong on 16/1/18.
 */
final public class UploadFile implements Serializable{

    private static final long serialVersionUID = 2L;

    private String fullPath;
    private String name;
    private String extensionsName;
    private byte[] content;
    private String httpEncoding = "UTF-8";
    private CanUseHttpConnect canUseHttpConnect = CanUseHttpConnect.NO;

    public UploadFile(String fullPath, String name, String extensionsName, byte[] content) {
        this.fullPath = fullPath;
        this.name = name;
        this.extensionsName = extensionsName;
        this.content = content;
    }

    public UploadFile(String fullPath, String name, String extensionsName, String httpEncoding, byte[] content) {
        this.fullPath = fullPath;
        this.name = name;
        this.extensionsName = extensionsName;
        this.httpEncoding = httpEncoding;
        this.content = content;
    }



    public UploadFile canUseHttpConnect(CanUseHttpConnect yesOrNo){
        this.canUseHttpConnect = yesOrNo;
        return this;
    }

    public Boolean canUseHttpConnect(){
        return this.canUseHttpConnect == CanUseHttpConnect.YES;
    }

    public static UploadFile formFile(String fullPath, String name, String extensionsName,File file) throws IOException {
        return formFile(fullPath, name, extensionsName, "UTF-8",file);
    }

    public static UploadFile formFile(String fullPath, String name, String extensionsName, String httpEncoding,File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        int len = fis.available();
        byte[] buffer =new byte[len];
        fis.read(buffer);
        fis.close();
        return formBytes(fullPath,name,extensionsName,httpEncoding,buffer);
    }

    public static UploadFile formBytes(String fullPath, String name, String extensionsName,String httpEncoding, byte[] bytes) throws IOException {
        return new UploadFile(fullPath,name,extensionsName,httpEncoding,bytes);
    }

    public String getFullPath() {
        return fullPath;
    }

    public String getName() {
        return name;
    }

    public String getExtensionsName() {
        return extensionsName;
    }

    public String getHttpEncoding() {
        return httpEncoding;
    }

    public byte[] getContent() {
        return content;
    }

    public Integer getContentLength() {
        return content.length;
    }

    public CanUseHttpConnect getCanUseHttpConnect() {
        return canUseHttpConnect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UploadFile that = (UploadFile) o;

        if (fullPath != null ? !fullPath.equals(that.fullPath) : that.fullPath != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (extensionsName != null ? !extensionsName.equals(that.extensionsName) : that.extensionsName != null)
            return false;
        if (!Arrays.equals(content, that.content)) return false;
        return canUseHttpConnect == that.canUseHttpConnect;

    }

    @Override
    public int hashCode() {
        int result = fullPath != null ? fullPath.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (extensionsName != null ? extensionsName.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(content);
        result = 31 * result + (canUseHttpConnect != null ? canUseHttpConnect.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UploadFile{" +
                "fullPath='" + fullPath + '\'' +
                ", name='" + name + '\'' +
                ", extensionsName='" + extensionsName + '\'' +
                ", canUseHttpConnect=" + canUseHttpConnect +
                '}';
    }
}
