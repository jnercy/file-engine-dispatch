package com.nextcont.ecm.fileengine.bean.request;

import com.nextcont.ecm.fileengine.bean.UploadType;
import com.nextcont.ecm.fileengine.util.LongUtils;
import com.nextcont.ecm.fileengine.util.StringUtils;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 14:01
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
public class RequestData{

    private String fileName;

    private String mimeType;

    private Long length;

    private String source;

    private long fileSize;

    private String converter;

    private String ftpUserName;

    private String ftpPassword;

    private String uploadType;

    public boolean checkFileInfo(){

        if(StringUtils.isEmpty(fileName))
            return false;
        if(StringUtils.isEmpty(mimeType))
            return false;
        if(LongUtils.isNon(length))
            return false;
        if(StringUtils.isEmpty(source))
            return false;
        if(StringUtils.isEmpty(converter))
            converter = ConvertType.local.name();

        return true;
    }

    public void completeFtpInfo() {
         if(StringUtils.isEmpty(ftpUserName))
             ftpUserName = "anonymous";
    }

    public boolean isPassCheck(){
        if (UploadType.getUploadType(uploadType) == UploadType.ftp) {
            completeFtpInfo();
            return checkFileInfo();
        }
        else if(UploadType.getUploadType(uploadType) == UploadType.http && checkFileInfo())
            return true;
        else if(UploadType.getUploadType(uploadType) == UploadType.dfs && checkFileInfo())
            return true;
        else
            return false;
    }


}
