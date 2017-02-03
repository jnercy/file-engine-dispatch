package com.nextcont.ecm.fileengine.bean.request;

import com.nextcont.ecm.fileengine.bean.Global;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
public class NotifyRequest extends Global {

    private String id;

    private String action;

    //urls
    private List<String> data;

    private String status;

    private String msg;

    private String fileName;

    private String source;
}
