package com.nextcont.ecm.fileengine.bean.response;

import com.nextcont.ecm.fileengine.bean.Global;
import com.nextcont.ecm.fileengine.constant.TransitionState;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
 */
@Data
public class UploadResponse extends Global {

    private String id;

    private String action;

    private TransitionState status;

    private String msg;

    private String fileName;

    private String source;
}
