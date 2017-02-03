package com.nextcont.ecm.fileengine.bean.response;

import com.nextcont.ecm.fileengine.util.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/10/18
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CallbackResponse {
    private String globalId;
    private String action;
    private CallbackData data;
    private String status;
    private String msg;
    private String fileName;
    private String source;

    public String convert2Json() {
        return (String)JsonFormat.convertJson(this).orElse("");
    }
}

