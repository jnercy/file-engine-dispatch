package com.nextcont.ecm.fileengine.business.event.transiton;

import com.google.common.eventbus.Subscribe;
import com.nextcont.ecm.fileengine.api.FileManager;
import com.nextcont.ecm.fileengine.bean.FileRecord;
import com.nextcont.ecm.fileengine.bean.TaskStatus;
import com.nextcont.ecm.fileengine.bean.request.TransRequest;
import com.nextcont.ecm.fileengine.util.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/29
 * Time: 11:24
 * To change this template use File | Settings | File Templates.
 */
@Component
public class UploadEvent {

    private static Logger logger = LoggerFactory.getLogger(UploadEvent.class);

    @Autowired
    private FileManager fastdfsFileManager;

    @Subscribe
    public void uploadFile(TransRequest request) {

        logger.info("uploadFile...");

        String fileRecordResponse = fastdfsFileManager.upload(request.getData()
                .getFileName(), request.getGlobalId(), request.getProcessRecord().getDownloadFile());
        Optional<FileRecord> optionUpload = JsonFormat.convert2Object(fileRecordResponse, new FileRecord());

        logger.info(optionUpload
                .map(FileRecord::toString)
                .orElse(""));

        //打印转换请求
        logger.info("temp file delete...");

        //删除临时文件
        boolean delete = request.getProcessRecord().getDownloadFile().delete();

        logger.info(delete ? "temp file delete success!!" : " temp file delete failed");

        request.getProcessRecord().setUploadStatus(TaskStatus.success);
    }


}
