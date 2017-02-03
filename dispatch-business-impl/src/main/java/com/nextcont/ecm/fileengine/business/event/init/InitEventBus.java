package com.nextcont.ecm.fileengine.business.event.init;

import com.nextcont.ecm.fileengine.business.event.bus.*;
import com.nextcont.ecm.fileengine.business.event.callback.CallbackExecuteEvent;
import com.nextcont.ecm.fileengine.business.event.callback.ValidateCallbackFailedEvent;
import com.nextcont.ecm.fileengine.business.event.callback.ValidateCallbackSuccessEvent;
import com.nextcont.ecm.fileengine.business.event.transiton.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/29
 * Time: 13:21
 * To change this template use File | Settings | File Templates.
 */
@Component
public class InitEventBus {


    @Autowired
    private ConvertEvent converEvent;

    @Autowired
    private DatabaseEvent databaseEvent;

    @Autowired
    private NetworkIoEvent networkIoEvent;

    @Autowired
    private PretreatmentEvent pretreatmentEvent;

    @Autowired
    private UploadEvent uploadEvent;

    @Autowired
    private CallbackExecuteEvent callbackExecuteEvent;

    @Autowired
    private ValidateCallbackSuccessEvent validateCallbackSuccessEvent;

    @Autowired
    private ValidateCallbackFailedEvent validateCallbackFailedEvent;


    @PostConstruct
    public void init() {

        //文件转换服务事件监测
        RequestEventBus.register(pretreatmentEvent);
        TransitionEventBus.register(Arrays.asList(networkIoEvent, uploadEvent, databaseEvent, converEvent));
        DfsTransitionEventBus.register(Arrays.asList(databaseEvent,converEvent));

        //回调服务事件监测
        CallbackSucessEventBus.register(Arrays.asList(validateCallbackSuccessEvent,callbackExecuteEvent));
        CallbackFailedEventBus.register(Arrays.asList(validateCallbackFailedEvent,callbackExecuteEvent));

    }
}
