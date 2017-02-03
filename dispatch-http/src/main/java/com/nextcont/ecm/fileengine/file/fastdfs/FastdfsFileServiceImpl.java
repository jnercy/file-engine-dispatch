package com.nextcont.ecm.fileengine.file.fastdfs;


import com.google.inject.Inject;
import com.nextcont.ecm.fileengine.bean.BusinessCode;
import com.nextcont.ecm.fileengine.bean.DfsException;
import com.nextcont.ecm.fileengine.bean.FileRecord;
import com.nextcont.ecm.fileengine.bean.request.UploadFile;
import com.nextcont.ecm.fileengine.bean.response.Response;
import com.nextcont.ecm.fileengine.common.AbstractLifeCycleComponent;
import com.nextcont.ecm.fileengine.fastdfs.FastdfsClient;
import com.nextcont.ecm.fileengine.fastdfs.FastdfsFile;
import com.nextcont.ecm.fileengine.file.DownloadResult;
import com.nextcont.ecm.fileengine.file.FileService;
import com.nextcont.ecm.fileengine.persistence.manager.FileRecordManager;
import com.nextcont.ecm.fileengine.setting.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 *
 * Created by wangxudong on 16/1/21.
 */
public class FastdfsFileServiceImpl extends AbstractLifeCycleComponent implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FastdfsFileServiceImpl.class);

    private final FastdfsClient fastdfsClient;
    private final FileRecordManager fileRecordManager;

    @Inject public FastdfsFileServiceImpl(Settings settings, FileRecordManager fileRecordManager){
        fastdfsClient = new FastdfsClient();
        fastdfsClient.setTrackerAddr(settings.get("fastdfs.tracker.addr","192.168.15.205"));
        this.fileRecordManager = fileRecordManager;
    }

    @Override
    protected void doStart() throws Exception {
        fastdfsClient.init();
    }

    @Override
    protected void doStop() throws Exception {
        fastdfsClient.destroy();
    }

    @Override
    public DownloadResult download(String path) {
        FileRecord fileRecord = fileRecordManager.findById(path);
        FastdfsFile fastdfsFile = FastdfsFile.parse(fileRecord.getFsRawValue());
        byte[] fileBytes = fastdfsClient.download(fastdfsFile);
        return new DownloadResult(fileRecord,fileBytes);
    }

    @Override
    public Response<FileRecord> upload(byte[] file,String fileName,String globalId) {
        try {
            String extensionsName = fileName.substring(fileName.lastIndexOf(".")+1);
            UploadFile uploadFile = new UploadFile(fileName,fileName,extensionsName,file);
            return getFileRecordResponse(globalId, uploadFile);
        } catch (DfsException e) {
            logger.error("fastdfs upload file error !", e);
            return Response.failure(BusinessCode.APPLICATION_ERROR, e);
        } catch (Throwable t) {
            logger.error("upload files error !", t);
            return Response.failure(BusinessCode.APPLICATION_ERROR, t);
        }
    }


    private Response<FileRecord> getFileRecordResponse(String globalId, UploadFile uploadFile) {
        FastdfsFile fastdfsFile = null;
        fastdfsFile = fastdfsClient.upload(uploadFile);

        FileRecord fileRecord = buildForm(uploadFile,fastdfsFile,globalId);

        fileRecordManager.insertFileRecord(fileRecord);

        logger.info(fileRecord.toString());
        return Response.success(fileRecord);
    }

    private FileRecord buildForm(UploadFile uploadFile ,FastdfsFile fastdfsFile,String globalId) {
        return new FileRecord(
                globalId,
                fastdfsFile.getFid(),
                fastdfsFile.getGroupName(),
                uploadFile.getFullPath(),
                uploadFile.getName(),
                uploadFile.getExtensionsName(),
                new Date(),
                new Date(),
                uploadFile.getCanUseHttpConnect(),
                fastdfsFile.rawValue(),
                uploadFile.getHttpEncoding()
        );
    }


}
