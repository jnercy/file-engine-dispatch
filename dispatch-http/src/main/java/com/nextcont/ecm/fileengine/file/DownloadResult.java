package com.nextcont.ecm.fileengine.file;


import com.nextcont.ecm.fileengine.bean.FileRecord;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class DownloadResult {

    private final FileRecord fileRecord;
    private final byte[] fileBytes;

    public DownloadResult(FileRecord fileRecord, byte[] fileBytes) {
        this.fileRecord = fileRecord;
        this.fileBytes = fileBytes;
    }

    public int len(){
        return fileBytes.length;
    }

    public boolean isNull(){
        return fileRecord==null || fileBytes == null;
    }

    public FileRecord getFileRecord() {
        return fileRecord;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }
}
