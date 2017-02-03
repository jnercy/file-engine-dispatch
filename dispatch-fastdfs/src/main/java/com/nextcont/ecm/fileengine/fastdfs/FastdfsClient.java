package com.nextcont.ecm.fileengine.fastdfs;

import com.nextcont.ecm.fileengine.bean.DfsException;
import com.nextcont.ecm.fileengine.bean.request.UploadFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * fastdfs的客户端
 * Created by wangxudong on 16/1/18.
 */
public class FastdfsClient {

    private static final Logger logger = LoggerFactory.getLogger(FastdfsClient.class);

    private String charset = "UTF-8";
    private Integer connectTimeout = 1000;
    private Integer networkTimeout = 3000;
    private String trackerAddr;
    private Integer defaultTrackerPort = 22122;

    public void init() {
        ClientGlobal.setG_charset(charset);
        ClientGlobal.setG_connect_timeout(connectTimeout);
        ClientGlobal.setG_network_timeout(networkTimeout);
        ClientGlobal.setG_anti_steal_token(false);
        ClientGlobal.setG_secret_key(null);
        ClientGlobal.setG_tracker_http_port(80);
        // Tracker服务器列表
        List<InetSocketAddress> collect = Stream.of(trackerAddr.split(","))
                .map(addr -> {
                    String[] addrSplit = addr.split(":");
                    return addrSplit.length == 1
                            ? new InetSocketAddress(addrSplit[0], defaultTrackerPort)
                            : new InetSocketAddress(addrSplit[0], Integer.valueOf(addrSplit[1]));
                }).collect(Collectors.toList());
        InetSocketAddress[] tracker_servers = collect.toArray(new InetSocketAddress[collect.size()]);
        ClientGlobal.setG_tracker_group(new TrackerGroup(tracker_servers));
    }

    public void destroy() {
    }


    public FastdfsFile upload(UploadFile uploadFile) {
        try {
            NameValuePair[] metaList = new NameValuePair[3];
            metaList[0] = new NameValuePair("fileName", uploadFile.getName());
            metaList[1] = new NameValuePair("fileExtName", uploadFile.getExtensionsName());
            metaList[2] = new NameValuePair("fileLength", String.valueOf(uploadFile.getContentLength()));
            StorageClient storageClient = new StorageClient();
            String[] files = storageClient.upload_file(uploadFile.getContent(), uploadFile.getExtensionsName(), metaList);
            return FastdfsFile.parse(files);
        } catch (Exception e) {
            logger.error("fastdfs error reason is ", e);
            throw new DfsException("fastdfs upload file error !", e);
        }
    }

    public byte[] download(FastdfsFile fastdfsFile){
        try {
            StorageClient storageClient = new StorageClient();
            return storageClient.download_file(fastdfsFile.getGroupName(),fastdfsFile.getFid());
        } catch (Exception e) {
            logger.error("fastdfs error reason is ", e);
            throw new DfsException("fastdfs download file error !", e);
        }
    }


    public void setCharset(String charset) {
        this.charset = charset;
    }


    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }


    public void setNetworkTimeout(Integer networkTimeout) {
        this.networkTimeout = networkTimeout;
    }

    public void setTrackerAddr(String trackerAddr) {
        this.trackerAddr = trackerAddr;
    }
}
