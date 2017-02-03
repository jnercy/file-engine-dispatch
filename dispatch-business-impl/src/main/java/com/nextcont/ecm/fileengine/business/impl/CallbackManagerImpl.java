package com.nextcont.ecm.fileengine.business.impl;

import com.nextcont.ecm.fileengine.api.CallbackManager;
import com.nextcont.ecm.fileengine.business.event.bean.CallbackEventRecord;
import com.nextcont.ecm.fileengine.business.event.bus.CallbackFailedEventBus;
import com.nextcont.ecm.fileengine.business.event.bus.CallbackSucessEventBus;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/10/10
 * Time: 11:23
 * To change this template use File | Settings | File Templates.
 */
@Service("callbackManager")
@Path("callback")
public class CallbackManagerImpl implements CallbackManager {

    @Override
    @POST
    @Path("success")
    @Consumes({javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({javax.ws.rs.core.MediaType.TEXT_HTML})
    public String notifySuccess(@FormParam("globalId") String globalId) {

        CallbackEventRecord record = new CallbackEventRecord();
        record.setGlobalId(globalId);
        CallbackSucessEventBus.post(record);
        return record.getCallbackMsg();
    }


    @Override
    @POST
    @Path("failed")
    @Consumes({javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({javax.ws.rs.core.MediaType.TEXT_HTML})
    public String notifyFailed(@FormParam("globalId") String globalId) {

        CallbackEventRecord record = new CallbackEventRecord();
        record.setGlobalId(globalId);
        CallbackFailedEventBus.post(record);
        return record.getCallbackMsg();
    }

}
