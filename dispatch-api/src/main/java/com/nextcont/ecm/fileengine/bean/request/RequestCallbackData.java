package com.nextcont.ecm.fileengine.bean.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/9
 * Time: 9:50
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
public class RequestCallbackData {

    private String callbackUrl;

    private String callbackType;

    private String websocketSessionId;
}
