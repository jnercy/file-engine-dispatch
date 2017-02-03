package com.nextcont.ecm.fileengine.bean.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nextcont.ecm.fileengine.bean.Global;
import com.nextcont.ecm.fileengine.bean.response.TransResponse;
import com.nextcont.ecm.fileengine.constant.TransitionState;
import com.nextcont.ecm.fileengine.util.StringUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
public class TransRequest extends Global {

    private String clientId;

    private String clientSecret;

    private RequestCallbackData callbackData;

    private RequestData data;

    private ProcessRecord processRecord;

    public void initProcessRecord(){
        processRecord = new ProcessRecord();
    }

    public TransResponse convert2Response(){

        TransResponse.TransResponseBuilder build = TransResponse.builder();
        TransResponse response = build
                .action("request")
                .source(data.getSource())
                .fileName(data.getFileName())
                .status(TransitionState.Result_SUCCESS)
                .msg(processRecord.getMessage())
                .build();

        response.setGlobalId(this.globalId);

        return response;
    }

    public TransResponse convert2ErrorResponse(){
        TransResponse.TransResponseBuilder build = TransResponse.builder();
        TransResponse tr = build
                .action("request")
                .source(data.getSource())
                .fileName(data.getFileName())
                .msg(processRecord.getMessage())
                .status(TransitionState.Result_ERROR)
                .build();
        tr.setGlobalId(this.globalId);
        return tr;
    }

    public boolean isPassCheck(){
        return data.isPassCheck();
    }





}
