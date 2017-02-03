package com.nextcont.ecm.fileengine.business.event.callback;

import com.google.common.eventbus.Subscribe;
import com.nextcont.ecm.fileengine.bean.TaskStatus;
import com.nextcont.ecm.fileengine.bean.request.CallbackType;
import com.nextcont.ecm.fileengine.bean.response.CallbackData;
import com.nextcont.ecm.fileengine.bean.response.CallbackResponse;
import com.nextcont.ecm.fileengine.business.event.bean.CallbackEventRecord;
import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionBean;
import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionCallbackBean;
import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionFilesBean;
import com.nextcont.ecm.fileengine.business.persistence.dao.TransitionCallbackDao;
import com.nextcont.ecm.fileengine.business.utils.SocketClient;
import com.nextcont.ecm.fileengine.util.DateTimeUtils;
import com.squareup.okhttp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/30
 * Time: 19:32
 * To change this template use File | Settings | File Templates.
 */
@Component
public class CallbackExecuteEvent {

    private static Logger logger = LoggerFactory.getLogger(CallbackExecuteEvent.class);

    @Autowired
    private TransitionCallbackDao transitionCallbackService;


    @Value("#{configProperties['websocket.server']}")
    private String WEBSOCKETURL;


    private final OkHttpClient client = new OkHttpClient();

    @Subscribe
    public void callbackExecute(CallbackEventRecord record){

        if(record.getValidateStatus()== TaskStatus.success ||record.getCallbackInterface()==TaskStatus.error) {

            CallbackResponse response;

            CallbackResponse.CallbackResponseBuilder builder = CallbackResponse.builder();

            TransitionBean transitionBean = record.getTransitonInfo();

            CallbackData data = buildCallbackData(record.getTransitionFilesInfo(), transitionBean.getWebsocketsessionid());

            response = builder.globalId(record.getGlobalId())
                    .data(data)
                    .action("notify")
                    .fileName(transitionBean.getFilename())
                    .status("success")
                    .source(transitionBean.getSource())
                    .msg(record.getCallbackMsg()).build();

            if (CallbackType.getCallbackType(transitionBean.getCallbacktype()) == CallbackType.websocket)
                record.setCallbackMsg(excuteWebSocketCallback(response.convert2Json()));

            else if (CallbackType.getCallbackType(transitionBean.getCallbacktype()) == CallbackType.http)
                record.setCallbackMsg(excuteHttpCallBack(transitionBean.getCallbackurl(), response.convert2Json()));

            logger.info("excuteHttpCallBack:{}", record.getCallbackMsg());

            transitionCallbackService
                    .insertSelective(buildRecordBean(record.getCallbackMsg(), record.getGlobalId(), transitionBean
                            .getCallbackurl(), "SUCCESS"));
        }
    }

    private CallbackData buildCallbackData(List<TransitionFilesBean> source, String websocketsessionid){
        CallbackData source2callbackData = new CallbackData();
        source2callbackData.setSessionId(websocketsessionid);
        source.forEach(fileInfo -> {
            switch (fileInfo.getType()){

                case "file":
                    source2callbackData.setFile(fileInfo.getUrl() != null ? fileInfo.getUrl() : "");
                    break;
                case "thumbnail":
                    source2callbackData.setThumbnail(fileInfo.getUrl() != null ? fileInfo.getUrl() : "");
                    break;
                case "preview":
                    source2callbackData.setPreview(fileInfo.getUrl() != null ? fileInfo.getUrl() : "");
                    break;
            }
        });
        return source2callbackData;
    }


    public String excuteHttpCallBack(String callbackUrl, String outputFileUrl){
        String callbackResult;
        try {
            RequestBody formBody = new FormEncodingBuilder()
                    .add("callbackUrl", outputFileUrl)
                    .build();
            Request request = new Request.Builder()
                    .url(callbackUrl)
                    .post(formBody)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful())
                callbackResult = response.body().string();
            else {
                callbackResult = "Unexpected code " + response;
                throw new IOException(callbackResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackResult = e.getMessage();
        }
        return callbackResult;
    }

    public String excuteWebSocketCallback(String outputInfo){
        Session session = prepareSocketConnect();
        try {
            session.getBasicRemote().sendText(outputInfo);
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "websocket excute error";
        }

        return "websocket callback success";
    }

    private Session prepareSocketConnect(){
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        logger.info("Connecting to"+ WEBSOCKETURL);
        Session session = null;
        try {
            session = container.connectToServer(SocketClient.class, URI.create(WEBSOCKETURL));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
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
