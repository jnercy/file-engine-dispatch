package com.nextcont.ecm.fileengine.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/3
 * Time: 10:47
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Getter
@AllArgsConstructor
public class FileMetaData implements Serializable{

    private String fileId;

    private Map<Object,Object> appProperties;

    private ContentHints contentHints;

    private String description;

    private String mimeType;

    private Date modifiedTime;

    private String folderColorRgb;

}
