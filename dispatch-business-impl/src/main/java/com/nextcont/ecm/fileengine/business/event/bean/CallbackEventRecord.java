package com.nextcont.ecm.fileengine.business.event.bean;

import com.nextcont.ecm.fileengine.bean.TaskStatus;
import com.nextcont.ecm.fileengine.bean.request.CallbackType;
import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionBean;
import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionFilesBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/30
 * Time: 18:40
 * To change this template use File | Settings | File Templates.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CallbackEventRecord {

    private String globalId;

    private List<TransitionFilesBean> transitionFilesInfo;

    private TaskStatus transitionFilesCompleted;

    private TransitionBean transitonInfo;

    private TaskStatus validateStatus;

    private CallbackType callbackType;

    private TaskStatus callbackPostStatus;

    //成功回调还是 失败回调
    private TaskStatus callbackInterface;

    private String callbackMsg;
}
