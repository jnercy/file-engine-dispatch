package com.nextcont.ecm.fileengine.business.event.transiton;

import com.google.common.eventbus.Subscribe;
import com.nextcont.ecm.fileengine.bean.TaskStatus;
import com.nextcont.ecm.fileengine.bean.Try;
import com.nextcont.ecm.fileengine.bean.UploadType;
import com.nextcont.ecm.fileengine.bean.request.TransRequest;
import com.nextcont.ecm.fileengine.business.utils.DownloadUtils;
import com.nextcont.ecm.fileengine.business.utils.FtpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/28
 * Time: 18:44
 * To change this template use File | Settings | File Templates.
 */
@Component
public class NetworkIoEvent {

    private static Logger logger = LoggerFactory.getLogger(NetworkIoEvent.class);

    private final String ERRORINFO = "file downloadError!!";

    private final String SUCCESSINFO = "file downloadSuccess!!";

    @Subscribe
    public void downloadFile(TransRequest request) {
        logger.info("{}: downloadFile...", request.getData().getUploadType());
        logger.info(Try.supplier(() -> {
            if (UploadType.getUploadType(request.getData().getUploadType()) == UploadType.http)
                request
                        .getProcessRecord()
                        .setDownloadFile(DownloadUtils.downLoadFromUrl(request.getData()
                                .getSource(), request.getData()
                                .getFileName()));
            else if (UploadType.getUploadType(request.getData().getUploadType()) == UploadType.ftp)
                request
                        .getProcessRecord()
                        .setDownloadFile(FtpUtils.downFile(request.getData().getSource(), request.getData()
                                .getFtpUserName(), request.getData().getFtpPassword()));
            else {
                request.getProcessRecord().setDownloadRecord(ERRORINFO);
                request.getData().setFileName(ERRORINFO);
                request.getProcessRecord().setFileDownloadStatus(TaskStatus.error);
                return ERRORINFO;
            }
            request.getProcessRecord().setFileDownloadStatus(TaskStatus.success);
            request.getData().setFileSize(request.getProcessRecord().getDownloadFile().length());
            return SUCCESSINFO;
        }).getOrThrow());
    }
}
