package com.nextcont.ecm.fileengine.bean.response;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/10/18
 * Time: 13:37
 * To change this template use File | Settings | File Templates.
 */
@Data
public class CallbackData {

    private String file = "";

    private String thumbnail = "";

    private String preview = "";

    private String sessionId = "";
}
