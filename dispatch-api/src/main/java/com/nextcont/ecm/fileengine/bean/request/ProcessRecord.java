package com.nextcont.ecm.fileengine.bean.request;

import com.nextcont.ecm.fileengine.bean.TaskStatus;
import lombok.Data;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/28
 * Time: 18:47
 * To change this template use File | Settings | File Templates.
 */
@Data
public class ProcessRecord {

    private String downloadRecord;

    private File downloadFile;

    private TaskStatus FileDownloadStatus;

    private String uploadRecord;

    private TaskStatus uploadStatus;

    private String transitonRecord;

    private TaskStatus transitonStatus;

    private String message;

    private boolean isCompleted;

}
