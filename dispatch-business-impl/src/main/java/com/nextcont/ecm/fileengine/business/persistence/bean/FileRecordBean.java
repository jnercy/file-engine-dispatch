
package com.nextcont.ecm.fileengine.business.persistence.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileRecordBean {

    private String globalId;

    private String fid;

    private String groupName;

    private String fileName;

    private String extensionsName;

    private Date createTime;

    private Date updateTime;

    private String httpEncoding;

    private String fsRawValue;

    private String path;

}
