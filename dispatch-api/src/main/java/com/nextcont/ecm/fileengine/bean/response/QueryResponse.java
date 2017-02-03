package com.nextcont.ecm.fileengine.bean.response;

import com.nextcont.ecm.fileengine.bean.Global;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
@Data
@Builder
public class QueryResponse extends Global {

    private String id;

    private String action;

    // 详细信息
    private List<QueryResponseInfo> data;


    private String fileName;

    private String source;
}
