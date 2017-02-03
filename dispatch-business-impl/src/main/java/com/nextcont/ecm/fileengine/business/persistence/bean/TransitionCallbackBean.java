package com.nextcont.ecm.fileengine.business.persistence.bean;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransitionCallbackBean {

    private String globalId;

    private String status;

    private String info;

    private String callbackUrl;

    private String createTime;

}