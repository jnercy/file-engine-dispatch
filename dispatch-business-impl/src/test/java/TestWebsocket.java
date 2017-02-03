import com.nextcont.ecm.fileengine.business.utils.SocketClient;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/9
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 */
public class TestWebsocket {

    public Session session;

    protected void start()
    {

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        String uri ="ws://localhost:8081/websocket/callback";
        System.out.println("Connecting to"+ uri);
        try {
            session = container.connectToServer(SocketClient.class, URI.create(uri));
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String args[]){
        TestWebsocket client = new TestWebsocket();
        client.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input ="";
        try {
            do{
                input = br.readLine();
                if(!input.equals("exit"))
                    client.session.getBasicRemote().sendText(input);

            }while(!input.equals("exit"));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
