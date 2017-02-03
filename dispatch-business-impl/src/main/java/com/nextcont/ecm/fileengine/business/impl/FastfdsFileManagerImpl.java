package com.nextcont.ecm.fileengine.business.impl;


import com.nextcont.ecm.fileengine.api.FileManager;
import com.squareup.okhttp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.net.ssl.*;
import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * 文件管理者的实现
 * Created by wangxudong on 16/1/18.
 */
@Service("fastdfsFileManager")
public class FastfdsFileManagerImpl implements FileManager {

    private static final Logger logger = LoggerFactory.getLogger(FastfdsFileManagerImpl.class);

    @Value("#{configProperties['fastdfs.server']}")
    private String dfsServer;

    private final String IMGUR_CLIENT_ID = "...";

    private OkHttpClient client;

    @PostConstruct
    public void init(){
        client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setWriteTimeout(300, TimeUnit.SECONDS);
        client.setReadTimeout(300, TimeUnit.SECONDS);
    }

    @Override
    public String upload(String fileName,String globalId, File file) {

        String uploadResult =  "";

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new MyX509TrustManager()}, new java.security.SecureRandom());
            client.setSslSocketFactory(sslContext.getSocketFactory());
            client.setHostnameVerifier(new MyHostnameVerifier());
            uploadResult = upload2http(fileName,globalId,file);
        } catch (NoSuchAlgorithmException | KeyManagementException | IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return uploadResult;
    }

    private String upload2http(String fileName,String globalId, File file) throws IOException {

        String uploadUrl = dfsServer +globalId;

        MediaType mediaType = MediaType.parse(contentType(fileName));

        RequestBody requestBody = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"title\""),
                        RequestBody.create(null, "Square Logo"))
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"image\";filename=\""+fileName+"\""),
                        RequestBody.create(mediaType,file))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .header("Connection", "Close")
                .url(uploadUrl)
                .post(requestBody)
                .build();

        com.squareup.okhttp.Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        String uploadResult = response.body().string();
        logger.info(uploadResult);

        return uploadResult;
    }

    private String contentType(String path) {
        if (path.endsWith(".png")) return "image/png";
        if (path.endsWith(".jpg")) return "image/jpeg";
        if (path.endsWith(".jpeg")) return "image/jpeg";
        if (path.endsWith(".gif")) return "image/gif";
        if (path.endsWith(".html")) return "text/html; charset=utf-8";
        if (path.endsWith(".txt")) return "text/plain; charset=utf-8";

        return "application/octet-stream";
    }

     class MyX509TrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // Log.d(tag, "check client trusted. authType=" + authType);
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // Log.d(tag, "check servlet trusted. authType=" + authType);
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            // Log.d(tag, "get acceptedissuer");
            return null;
        }

    }

     class MyHostnameVerifier implements HostnameVerifier {

        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

}
