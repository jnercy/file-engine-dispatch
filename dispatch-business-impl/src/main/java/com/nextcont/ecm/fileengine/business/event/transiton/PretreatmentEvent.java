package com.nextcont.ecm.fileengine.business.event.transiton;

import com.google.common.eventbus.Subscribe;
import com.nextcont.ecm.fileengine.bean.TaskStatus;
import com.nextcont.ecm.fileengine.bean.UploadType;
import com.nextcont.ecm.fileengine.bean.request.TransRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/29
 * Time: 10:25
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PretreatmentEvent {

    private static Logger logger = LoggerFactory.getLogger(PretreatmentEvent.class);


    @Subscribe
    public void pretreatmentRequest(TransRequest request){

        request.initProcessRecord();

        if (UploadType.getUploadType(request.getData().getUploadType())==UploadType.dfs) {
            request.setGlobalId(request.getData().getSource());
            request.getProcessRecord().setFileDownloadStatus(TaskStatus.success);
        }
        else{
            request.createGlobalId();
            logger.info("createId:"+ request.getGlobalId());
            request.getProcessRecord().setMessage(request.getData().getUploadType()+"-channel transition request commit!!");
        }

    }


}
