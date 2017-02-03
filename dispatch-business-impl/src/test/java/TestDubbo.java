import com.nextcont.ecm.fileengine.api.FileManager;
import com.nextcont.ecm.fileengine.bean.request.QueryRequest;
import com.nextcont.ecm.fileengine.video.transcode.api.IConvertService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
//        "classpath:spring-business.xml",
        "classpath:spring-consumer.xml"}
)
public class TestDubbo {

    @Autowired
    private IConvertService convertService;

    @Autowired
    private FileManager fileManager;

    @Test
    public void testFile(){
        convertService.cancelConvert(null);
        System.out.println();
    }

    @Test
    public void testSearch(){
        System.out.println(false);
        QueryRequest request =new QueryRequest();
        request.setGlobalId("1a90ac5736b6458ba06f6d800678e087");
//        aliyunService.queryVideoTranscodeProgress();
    }



    



}
