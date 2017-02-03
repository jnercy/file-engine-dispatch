package com.nextcont.ecm.fileengine.bean;

import com.nextcont.ecm.fileengine.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class FileRecord implements Serializable{

    private static final long serialVersionUID = 2L;

    private String globalId;

    private String fid;

    private String groupName;


    private String path;


    private String fileName;


    private String extensionsName;


    private Date createTime;


    private Date updateTime;


    private CanUseHttpConnect canUseHttpConnect;

    private String httpEncoding;

    private String fsRawValue;


    public void createGlobalId(){
        this.globalId = UUID.randomUUID().toString();
    }

    public boolean checkGlobalId(){
        return StringUtils.isNotEmpty(globalId);
    }

    public FileRecord(String globalId,String fid,String groupName, String path,String fileName, String extensionsName
            , Date createTime, Date updateTime, CanUseHttpConnect canUseHttpConnect, String fsRawValue, String httpEncoding){
        if(StringUtils.isNotEmpty(globalId) && globalId.length()==36)
            this.globalId = globalId;
        else
            this.createGlobalId();
        this.fid = fid;
        this.groupName = groupName;
        this.path = path;
        this.fileName = fileName;
        this.extensionsName = extensionsName;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.canUseHttpConnect = canUseHttpConnect;
        this.httpEncoding = httpEncoding;
        this.fsRawValue = fsRawValue;
    }

    public FileRecord() {
    }

    public String getFid() {
        return fid;
    }

    public String getPath() {
        return path;
    }

    public String getFileName() {
        return fileName;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getExtensionsName() {
        return extensionsName;
    }

    public String getFullName(){
        return fileName+"."+extensionsName;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public Date getUpdateTime() {
        return updateTime;
    }


    public CanUseHttpConnect getCanUseHttpConnect() {
        return canUseHttpConnect;
    }

    public boolean canUseHttpConnect(){
        return canUseHttpConnect == CanUseHttpConnect.YES;
    }

    public boolean canNotUseHttpConnect(){
        return canUseHttpConnect == CanUseHttpConnect.NO;
    }

    public String getFsRawValue() {
        return fsRawValue;
    }

    public String getHttpEncoding() {
        return httpEncoding;
    }

    public String getGlobalId() {
        return globalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileRecord that = (FileRecord) o;

        if (fid != null ? !fid.equals(that.fid) : that.fid != null) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (extensionsName != null ? !extensionsName.equals(that.extensionsName) : that.extensionsName != null)
            return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (canUseHttpConnect != that.canUseHttpConnect) return false;
        if (httpEncoding != null ? !httpEncoding.equals(that.httpEncoding) : that.httpEncoding != null) return false;
        return fsRawValue != null ? fsRawValue.equals(that.fsRawValue) : that.fsRawValue == null;

    }

    @Override
    public int hashCode() {
        int result = fid != null ? fid.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (extensionsName != null ? extensionsName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (canUseHttpConnect != null ? canUseHttpConnect.hashCode() : 0);
        result = 31 * result + (httpEncoding != null ? httpEncoding.hashCode() : 0);
        result = 31 * result + (fsRawValue != null ? fsRawValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FileRecord{" +
                "globalId='" + globalId + '\'' +
                "fid='" + fid + '\'' +
                ", path='" + path + '\'' +
                ", fileName='" + fileName + '\'' +
                ", extensionsName='" + extensionsName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", canUseHttpConnect=" + canUseHttpConnect +
                ", httpEncoding='" + httpEncoding + '\'' +
                ", fsRawValue='" + fsRawValue + '\'' +
                '}';
    }
}