package com.nextcont.ecm.fileengine.business.utils;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/27
 * Time: 10:36
 * To change this template use File | Settings | File Templates.
 */
public class DownloadUtils {


//    public static InputStream downloadFile(String url){
//        InputStream in = null;
//
//        try {
//            URL theURL = new URL(url);
//            URLConnection connection = theURL.openConnection();
//            in = connection.getInputStream();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return in;
//    }


    public static File  downLoadFromUrl(String urlStr,String fileName){
        File file = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(30 * 1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            //得到输入流
            InputStream inputStream = conn.getInputStream();
            //获取自己数组
            byte[] getData = readInputStream(inputStream);

            //文件保存位置
            file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);
            if (fos != null) {
                fos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }

            System.out.println("info:" + url + " download success");
            System.out.println(file.length());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return file;


    }

    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }



    public static File stream2File(InputStream in,String fileName) throws IOException {
        byte[] getData = readInputStream(in);

        //文件保存位置
        File file = new File("D://pic//"+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(in!=null){
            in.close();
        }

        System.out.println("info: download success");
        System.out.println(file.length());
        return file;
    }
}
