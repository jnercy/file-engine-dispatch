import com.google.common.eventbus.EventBus;
import com.nextcont.ecm.fileengine.bean.request.CallbackType;
import com.nextcont.ecm.fileengine.bean.request.RequestCallbackData;
import com.nextcont.ecm.fileengine.bean.request.RequestData;
import com.nextcont.ecm.fileengine.bean.request.TransRequest;
import com.nextcont.ecm.fileengine.business.event.transiton.DatabaseEvent;
import com.nextcont.ecm.fileengine.business.event.transiton.NetworkIoEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/28
 * Time: 14:49
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:mybatis-config.xml",
        "classpath:spring-data.xml",
        "classpath:spring-event.xml"}
)
public class EventTest {

    @Autowired
    private DatabaseEvent event;

    @Autowired
    private NetworkIoEvent networkIoEvent;

    @Autowired
    private EventBus bus;

    @Test
    public void functionEventTest() {

        bus.register(event);
        bus.register(networkIoEvent);

        TransRequest tr = new TransRequest();
        tr.createGlobalId();
        RequestCallbackData callbackData = new RequestCallbackData();
        callbackData.setCallbackType(CallbackType.http.name());
        callbackData.setCallbackUrl("www.baidu.com");
        tr.setCallbackData(callbackData);
        RequestData data = new RequestData();
        data.setFileName("movie.mp4");
        data.setLength(256L);
        data.setMimeType("test");
        data.setSource("www.baidu.com");
        data.setFileSize(100L);
        tr.setData(data);
        tr.setClientId("123");

        bus.post(tr);

    }
}
