package com.nextcont.ecm.fileengine.business.event.callback;

import com.google.common.eventbus.Subscribe;
import com.nextcont.ecm.fileengine.bean.TaskStatus;
import com.nextcont.ecm.fileengine.business.event.bean.CallbackEventRecord;
import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionBean;
import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionFilesBean;
import com.nextcont.ecm.fileengine.business.persistence.dao.TransitionDao;
import com.nextcont.ecm.fileengine.business.persistence.dao.TransitionFileDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/12/1
 * Time: 16:26
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ValidateCallbackFailedEvent {

    private static Logger logger = LoggerFactory.getLogger(ValidateCallbackFailedEvent.class);


    @Autowired
    private TransitionFileDao transitionFileService;

    @Autowired
    private TransitionDao transitionService;


    @Subscribe
    public void validateData(CallbackEventRecord request){

        TransitionBean transiton = transitionService.selectByGlobalId(request.getGlobalId());

        List<TransitionFilesBean> transitionFiles = transitionFileService.selectByGlobalId(request.getGlobalId());

        request.setTransitionFilesInfo(transitionFiles);
        request.setTransitonInfo(transiton);

        logger.info("notify-failed start:globalId[{}],callbackUrl[{}],errorMsg[{}]",request.getGlobalId(),transiton.getCallbackurl(),transiton.getErrorMsg());
        request.setTransitonInfo(transiton);
        request.setCallbackInterface(TaskStatus.error);
    }

}
