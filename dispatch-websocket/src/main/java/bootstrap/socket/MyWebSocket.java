package bootstrap.socket;


import bootstrap.config.CustomSpringConfigurator;
import bootstrap.service.IWsExecuteService;
import com.nextcont.ecm.fileengine.bean.request.TransRequest;
import com.nextcont.ecm.fileengine.bean.response.CallbackResponse;
import com.nextcont.ecm.fileengine.util.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/10/31
 * Time: 16:30
 * To change this template use File | Settings | File Templates.
 */
@ServerEndpoint(value = "/websocket/{action}", configurator = CustomSpringConfigurator.class)
@Component
public class MyWebSocket {

    private static int onlineCount = 0;

//    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    private Map<Session,String> sessionMap = new ConcurrentHashMap<>();

    private Logger logger = LoggerFactory.getLogger(MyWebSocket.class);

    private Session session;

    @Autowired
    private IWsExecuteService wsExecuteService;

    //接收到ws连接
    @OnOpen
    public void onOpen (Session session){
        session.setMaxBinaryMessageBufferSize(1024*100);
        sessionMap.put(session, UUID.randomUUID().toString());
        this.session = session;
        addOnlineCount();
        System.out.println("有新链接加入!当前在线人数为" + getOnlineCount());
    }

    //关闭ws连接
    @OnClose
    public void onClose (){
        sessionMap.remove(this.session);
        subOnlineCount();
        System.out.println("有一链接关闭!当前在线人数为" + getOnlineCount());
    }

    //接受到消息
    @OnMessage
    public void onMessage (String message, Session session, @PathParam("action") String action) throws IOException {
        System.out.println("来自客户端的消息:" + message);

        if(SocketActionType.getSocketActionType(action)==SocketActionType.transition){
            TransRequest request = JsonFormat.convert2Object(message,new TransRequest()).orElse(null);

            if(request!=null){
                request.getCallbackData().setWebsocketSessionId(sessionMap.get(session));
                session.getBasicRemote().sendText(wsExecuteService.transtion(request));
            }
            else
                session.getBasicRemote().sendText(message);
        }
        else if(SocketActionType.getSocketActionType(action)==SocketActionType.callback){
            logger.debug(message);
            CallbackResponse callbackResponse = JsonFormat.convert2Object(message,new CallbackResponse()).orElse(null);
            String sessionId = callbackResponse.getData().getSessionId();
            for(Map.Entry<Session,String> t : sessionMap.entrySet()){
                if(t.getValue().equals(sessionId))
                    t.getKey().getBasicRemote().sendText(message);
            }
        }
    }


    public static synchronized  int getOnlineCount (){
        return MyWebSocket.onlineCount;
    }

    public static synchronized void addOnlineCount (){
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount (){
        MyWebSocket.onlineCount--;
    }

}