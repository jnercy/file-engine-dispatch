package com.nextcont.ecm.fileengine.business.event.transiton;

import com.google.common.eventbus.Subscribe;
import com.nextcont.ecm.fileengine.bean.TaskStatus;
import com.nextcont.ecm.fileengine.bean.Try;
import com.nextcont.ecm.fileengine.bean.request.TransRequest;
import com.nextcont.ecm.fileengine.business.persistence.dao.TransitionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/28
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */
@Component
public class DatabaseEvent {

    private static Logger logger = LoggerFactory.getLogger(DatabaseEvent.class);

    @Autowired
    private TransitionDao transitionService;

    @Subscribe
    public void insert2Transition(TransRequest request) {

        //添加transition记录
        Try.supplier(() -> {
            logger.info("record transitionAction...");
            return transitionService.insert(request);
        });

        request.getProcessRecord().setTransitonStatus(TaskStatus.success);

    }
}
