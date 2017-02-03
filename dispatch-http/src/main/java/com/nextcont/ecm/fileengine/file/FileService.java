package com.nextcont.ecm.fileengine.file;

import com.nextcont.ecm.fileengine.bean.FileRecord;
import com.nextcont.ecm.fileengine.bean.response.Response;
import com.nextcont.ecm.fileengine.common.LifeCycleComponent;
import io.netty.handler.codec.http.multipart.FileUpload;


/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public interface FileService extends LifeCycleComponent {

    DownloadResult download(String path);

    Response<FileRecord> upload(byte[] file,String fileName,String globalId);

}
