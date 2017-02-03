package com.nextcont.ecm.fileengine.bean.request;

import com.nextcont.ecm.fileengine.bean.Global;
import com.nextcont.ecm.fileengine.bean.response.CancelResponse;
import com.nextcont.ecm.fileengine.constant.TransitionState;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
public class CancelRequest extends Global {

    private String clientId;

    private String clientSecret;

    private String id;


    public CancelResponse convert2Response() {
        CancelResponse.CancelResponseBuilder builder = CancelResponse.builder();
        CancelResponse cr =  builder
                .id(this.id)
                .status(TransitionState.Result_SUCCESS)
                .build();
        cr.setGlobalId(this.id);
        return cr;
    }

    public CancelResponse convert2ErrorResponse(){
        CancelResponse cr = convert2Response();
        cr.setStatus(TransitionState.Result_ERROR);
        return cr;
    }

}
