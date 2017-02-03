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
public class TransitionBean {

    private String globalId;

    private Date createdate;

    private String requesttype;

    private String clientid;

    private String filename;

    private Long length;

    private String mimetype;

    private String source;

    private String callbackurl;

    private String md5digest;

    private String alijobid;

    private Integer filesize;

    private String callbacktype;

    private String websocketsessionid;

    private String errorMsg;

    private String location;

}