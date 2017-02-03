package com.nextcont.ecm.fileengine.business.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/27
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class FtpUtils {

    public static File downFile(String url,String username, String password) {

        Optional<FtpInfo> info = FtpInfo.dispatchFtp(url);

        final List<File> resultFile = new ArrayList<>();

        info.ifPresent(ftpInfo -> {

            File tempFile = null;

            FTPClient ftpClient = new FTPClient();
            try {

                int reply;
                ftpClient.setControlEncoding("UTF-8");

            /*
             *  为了上传和下载中文文件，有些地方建议使用以下两句代替
             *  new String(remotePath.getBytes(encoding),"iso-8859-1")转码。
             *  经过测试，通不过。
             */
//            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
//            conf.setServerLanguageCode("zh");

                ftpClient.connect(ftpInfo.getIp(), Integer.valueOf(ftpInfo.getPort()));
                // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
                ftpClient.login(username, password);// 登录
                // 设置文件传输类型为二进制
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                // 获取ftp登录应答代码
                reply = ftpClient.getReplyCode();

//            ftpClient.changeWorkingDirectory(new String(remotePath.getBytes("UTF-8"),"iso-8859-1"));
                // 获取文件列表
                ftpClient.changeWorkingDirectory(ftpInfo.getFilePath());
                FTPFile[] fs = ftpClient.listFiles();
                for (FTPFile ff : fs) {
                    System.out.println(ff.getName());
                    if (ff.getName().equals(ftpInfo.getFileName())) {
                        tempFile = new File(ftpInfo.getFileName());
                        OutputStream is = new FileOutputStream(tempFile);
                        ftpClient.retrieveFile(ff.getName(), is);
                        is.close();
                        resultFile.add(tempFile);
                    }
                }

                ftpClient.logout();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (ftpClient.isConnected()) {
                    try {
                        ftpClient.disconnect();
                    } catch (IOException ioe) {
                    }
                }
            }
        }
        );
        return resultFile.size()>0 ? resultFile.get(0) : null;
    }

    public static void main(String[] args) {
//        downFile("192.168.15.173", 21, "anonymous",
//                "123qweASD", "/video", "5m.avi", "D:/");
//
//        String url = "ftp://192.168.15.173/video/5minutes/5m.avi";
//        url = url.replace("ftp://","");
//        int count = url.indexOf("/");
//        String ip = url.substring(0,url.indexOf("/"));
//        String pathUrl = url.substring(count,url.length());
//        String fileName = pathUrl.substring(pathUrl.lastIndexOf("/")+1,pathUrl.length());
//        System.out.println(ip);
//        System.out.println(url);
//        System.out.println(pathUrl);
//        System.out.println(fileName);
        downFile("ftp://192.168.15.173/video/5minutes/5m.avi","anonymous","");
    }
}
