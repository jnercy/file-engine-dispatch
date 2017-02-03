package com.nextcont.ecm.fileengine.bean.response;

import com.nextcont.ecm.fileengine.bean.Global;
import com.nextcont.ecm.fileengine.constant.TransitionState;
import lombok.*;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransResponse extends Global {

    private String action;

    private TransitionState status;

    private String msg;

    private String fileName;

    private String source;

}
