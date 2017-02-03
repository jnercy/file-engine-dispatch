package com.nextcont.ecm.fileengine.business.utils;

import lombok.Data;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/10/17
 * Time: 14:29
 * To change this template use File | Settings | File Templates.
 */
@Data
public class FtpInfo {

    private String ip;

    private String port;

    private String FilePath;

    private String fileName;

    public static boolean checkFtpUrl(String url){
        return url.contains("ftp://");
    }

    public static Optional<FtpInfo> dispatchFtp(String url){
        return checkFtpUrl(url) ? Optional.of(url2Info(url)) : Optional.empty();
    }


    public static FtpInfo url2Info(String url){
        FtpInfo result = new FtpInfo();

        url = url.replace("ftp://","");

        int ipSplitIndex = url.indexOf("/");
        String fullIp = url.substring(0,ipSplitIndex);
        if(fullIp.indexOf(":")>0){
            String ipInfo [] = fullIp.split(":");
            result.setIp(ipInfo[0]);
            result.setPort(ipInfo[1]);
        }
        else{
            result.setIp(fullIp);
            result.setPort("21");
        }
        String totalFilePath = url.substring(ipSplitIndex,url.length());
        result.setFileName(totalFilePath.substring(totalFilePath.lastIndexOf("/")+1,totalFilePath.length()));
        result.setFilePath(totalFilePath.replace(result.getFileName(),""));

        return result;
    }

//    public static void main(String[] args) {
//        Optional<FtpInfo> demo = dispatchFtp("ftp://192.168.15.173:22/video/5minutes/5m.avi");
//        System.out.println();
//    }





}
