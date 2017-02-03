import com.nextcont.ecm.fileengine.api.TransitionManager;
import com.nextcont.ecm.fileengine.bean.request.TransRequest;
import com.nextcont.ecm.fileengine.util.JsonFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/8
 * Time: 10:12
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DubboTest.class)
@ImportResource({"classpath:spring-consumer.xml"})
public class DubboTest {


    @Autowired
    private TransitionManager transitionManager;

    @Test
    public void run(){
        System.out.println(transitionManager);

    }


}
