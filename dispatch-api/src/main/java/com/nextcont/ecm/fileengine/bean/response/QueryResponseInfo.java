package com.nextcont.ecm.fileengine.bean.response;

import com.nextcont.ecm.fileengine.constant.TransitionState;
import lombok.Data;

@Data
public class QueryResponseInfo {
	
    private String url;

    private TransitionState status;

    private String msg;

}
