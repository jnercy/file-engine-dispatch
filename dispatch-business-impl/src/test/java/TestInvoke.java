import com.nextcont.ecm.fileengine.api.TransitionManager;

import com.nextcont.ecm.fileengine.bean.request.QueryRequest;
import com.nextcont.ecm.fileengine.bean.request.RequestData;
import com.nextcont.ecm.fileengine.bean.request.TransRequest;
import com.nextcont.ecm.fileengine.bean.response.TransResponse;
import com.nextcont.ecm.fileengine.entity.QueryTransitionRes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/23
 * Time: 9:52
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring-fastdfs.xml",
        "classpath:spring-business.xml",
        "classpath:spring-consumer.xml",
        "classpath:mybatis-config.xml",
        "classpath:spring-data.xml"})
public class TestInvoke {

    @Autowired
    TransitionManager transitionManager;

    @Test
    public void invokeTest(){
        TransRequest request = new TransRequest();
        request.createGlobalId();
        RequestData data = new RequestData();
        data.setSource("http://114.80.184.42/youku/69765650ECA4281615683D5C9D/03000801005786E1D5759513A7ECB9AFABFD3B-CF66-3D4D-C29B-04B4AA9BE41B.mp4");
        data.setFileName("03000801005786E1D5759513A7ECB9AFABFD3B-CF66-3D4D-C29B-04B4AA9BE41B.mp4");
        data.setLength(128L);
        data.setMimeType("mp4");
        data.setUploadType("http");
        request.setData(data);

        TransResponse result = null;
        try {
            result = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    @Test
    public void invokeQueryTest(){
        QueryRequest request = new QueryRequest();
        request.setGlobalId("8069b7fe-c0d3-4d6e-bcff-9f00aab33a15");
        QueryTransitionRes result = transitionManager.query(request);
        System.out.println(result);
    }


    @Test
    public void invokeThread() throws InterruptedException {

    }




}
