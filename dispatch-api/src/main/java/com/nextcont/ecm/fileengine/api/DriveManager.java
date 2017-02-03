package com.nextcont.ecm.fileengine.api;

import com.nextcont.ecm.fileengine.bean.request.FileListRequest;
import com.nextcont.ecm.fileengine.bean.request.FileLockRequest;
import com.nextcont.ecm.fileengine.bean.request.FileShareRequest;
import com.nextcont.ecm.fileengine.file.FileAggregation;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/13
 * Time: 10:29
 * To change this template use File | Settings | File Templates.
 */
public interface DriveManager {


    List<FileAggregation> get(String fileId);

    List<FileAggregation> list(FileListRequest request);

    String lock(FileLockRequest reuqest,String fileId);

    String share(FileShareRequest request,String fileId);
}
