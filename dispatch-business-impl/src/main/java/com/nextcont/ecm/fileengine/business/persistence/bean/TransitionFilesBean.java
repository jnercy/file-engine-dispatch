package com.nextcont.ecm.fileengine.business.persistence.bean;

import lombok.Data;

@Data
public class TransitionFilesBean {

    private String globalId;

    private String url;

    private String type;

    private Long duration;

    private String location;

    private String fullpath;

    private String status;

    private String errormsg;

}