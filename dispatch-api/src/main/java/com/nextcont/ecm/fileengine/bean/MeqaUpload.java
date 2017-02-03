package com.nextcont.ecm.fileengine.bean;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/10/24
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 */
@Data
@Builder
public class MeqaUpload implements Serializable {

    private String globalId;

    private String fileName;

    private String mimeType;

    private String contentLength;

    private Integer shardSize;

    private Date createTime;

    private Date updateTime;

    private Integer currentShard;

    private Integer partFileSize;

    public MeqaUpload createGlobalId(){
        this.globalId = UUID.randomUUID().toString();
        return this;
    }
}
