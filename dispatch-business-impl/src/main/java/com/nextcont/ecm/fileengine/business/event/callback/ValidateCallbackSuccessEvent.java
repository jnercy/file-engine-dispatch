package com.nextcont.ecm.fileengine.business.event.callback;

import com.google.common.eventbus.Subscribe;
import com.nextcont.ecm.fileengine.bean.TaskStatus;
import com.nextcont.ecm.fileengine.business.event.bean.CallbackEventRecord;
import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionBean;
import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionCallbackBean;
import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionFilesBean;
import com.nextcont.ecm.fileengine.business.persistence.dao.TransitionCallbackDao;
import com.nextcont.ecm.fileengine.business.persistence.dao.TransitionDao;
import com.nextcont.ecm.fileengine.business.persistence.dao.TransitionFileDao;
import com.nextcont.ecm.fileengine.util.DateTimeUtils;
import com.nextcont.ecm.fileengine.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/30
 * Time: 18:29
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ValidateCallbackSuccessEvent {

    private static Logger logger = LoggerFactory.getLogger(ValidateCallbackSuccessEvent.class);


    @Autowired
    private TransitionFileDao transitionFileService;

    @Autowired
    private TransitionCallbackDao transitionCallbackService;

    @Autowired
    private TransitionDao transitionService;

    private final String GLOBALERRORINFO = "errorInfo : globalId error!!";

    private final String STATUSERRORINFO = "errorInfo : status   error!!";


    @Subscribe
    public void validateData(CallbackEventRecord callbackRequest){

        logger.info("notify-success start:globalId[{}],callbackUrl[{}]",callbackRequest.getGlobalId());

        List<TransitionFilesBean> transitionFiles = transitionFileService.selectByGlobalId(callbackRequest.getGlobalId());

        TransitionBean transiton = transitionService.selectByGlobalId(callbackRequest.getGlobalId());

        callbackRequest.setTransitionFilesInfo(transitionFiles);
        callbackRequest.setTransitonInfo(transiton);

        long successCount = transitionFiles
                .stream()
                .filter(filesBean -> StringUtils.isNotEmpty(filesBean.getStatus())&&filesBean.getStatus().equals("SUCCESS"))
                .count();

        if(transitionFiles.size()==0 || successCount==0){
            callbackRequest.setCallbackMsg(transitionFiles.size() == 0 ? GLOBALERRORINFO : STATUSERRORINFO);
            transitionCallbackService
                    .insertSelective(buildRecordBean(callbackRequest.getCallbackMsg(),callbackRequest.getGlobalId(),transiton.getCallbackurl(),"ERROR"));
            callbackRequest.setValidateStatus(TaskStatus.error);
        }
        else if(successCount == new Integer(transitionFiles.size()).longValue())
            callbackRequest.setValidateStatus(TaskStatus.success);
    }


    public TransitionCallbackBean buildRecordBean(String info, String globalId, String callBackUrl, String status){

        TransitionCallbackBean.TransitionCallbackBeanBuilder builder = TransitionCallbackBean.builder();

        builder
                .globalId(globalId)
                .callbackUrl(callBackUrl)
                .status(status)
                .info(info)
                .createTime(DateTimeUtils.now(false));

        return builder.build();
    }






}
