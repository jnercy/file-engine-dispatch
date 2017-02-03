package com.nextcont.ecm.fileengine.bean.request;

import lombok.Data;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/28
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
@Data
public class DownLoadFile implements Serializable {

    private String groupName;

    private String fid;

    private byte[] result;

    public DownLoadFile(String groupName, String fid, byte[] result) {
        this.groupName = groupName;
        this.fid = fid;
        this.result = result;
    }
}
