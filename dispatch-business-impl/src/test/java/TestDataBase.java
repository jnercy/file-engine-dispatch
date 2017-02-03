import com.nextcont.ecm.fileengine.bean.request.RequestData;
import com.nextcont.ecm.fileengine.bean.request.TransRequest;
import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionBean;
import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionCallbackBean;
import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionFilesBean;
import com.nextcont.ecm.fileengine.business.persistence.dao.TransitionCallbackDao;
import com.nextcont.ecm.fileengine.business.persistence.dao.TransitionDao;
import com.nextcont.ecm.fileengine.business.persistence.dao.TransitionFileDao;
import com.nextcont.ecm.fileengine.util.DateTimeUtils;
import com.nextcont.ecm.fileengine.util.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/22
 * Time: 10:22
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:mybatis-config.xml",
        "classpath:spring-data.xml"}
)
public class TestDataBase {

    @Autowired
    private TransitionDao transitionService;

    @Autowired
    private TransitionFileDao transitionFileService;

    @Autowired
    private TransitionCallbackDao transitionCallbackService;

    @Test
    public void testSelect() {
        List<TransitionBean> result = transitionService.getAll();
        System.out.println();
    }

    @Test
    public void testSelectCallback(){
//        List<TransitionCallbackBean> result = transitionCallbackService.selectByGlobalId("aa");
        TransitionCallbackBean.TransitionCallbackBeanBuilder builder = TransitionCallbackBean.builder();

        transitionCallbackService.insertSelective(builder
                .globalId("aabb")
                .callbackUrl("baidu.com")
                .status("success")
                .info("")
                .createTime(DateTimeUtils.now(false)).build());
        System.out.println();
    }

    @Test
    public void testSelectFile(){
        List<TransitionFilesBean> result = transitionFileService.selectByGlobalId("647db07b-315f-4f9b-bd4c-5c0692bfb09b");
        long successCount = result
                .stream()
                .filter(filesBean -> StringUtils.isNotEmpty(filesBean.getStatus())&&filesBean.getStatus().equals("SUCCESS"))
                .count();
        new Long(result.size());
        System.out.println();
    }

    @Test
    public void testInsert(){
        TransRequest tr = new TransRequest();
        tr.createGlobalId();
        RequestData data = new RequestData();
        data.setFileName("movie.mp4");
        data.setLength(256L);
        data.setMimeType("test");
        data.setSource("www.baidu.com");
        data.setFileSize(100L);
        tr.setData(data);
        System.out.println(transitionService.insert(tr));
    }

}
