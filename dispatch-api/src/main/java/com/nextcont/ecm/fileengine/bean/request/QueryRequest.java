package com.nextcont.ecm.fileengine.bean.request;

import com.nextcont.ecm.fileengine.bean.Global;
import com.nextcont.ecm.fileengine.bean.response.QueryResponse;
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
public class QueryRequest extends Global {

    private String clientId;

    private String clientSecret;

    private String id;



    public QueryResponse convert2Response(){
        QueryResponse.QueryResponseBuilder builder =  QueryResponse.builder();
        QueryResponse qr = builder
                .id(id)
                .action("query")
                .build();
        qr.setGlobalId(this.globalId);
        return qr;
    }

}
