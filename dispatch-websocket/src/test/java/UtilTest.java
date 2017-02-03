import com.nextcont.ecm.fileengine.bean.request.TransRequest;
import com.nextcont.ecm.fileengine.util.JsonFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/8
 * Time: 15:56
 * To change this template use File | Settings | File Templates.
 */
public class UtilTest {


    public static void main(String[] args) {
        TransRequest request = JsonFormat.convert2Object("{\n" +
                "    \"clientId\": \"\",\n" +
                "    \"clientSecret\": \"\",\n" +
                "    \"callbackUrl\": \"www.baidu.com\",\n" +
                "    \"data\":{\n" +
                "      \"fileName\":\"movie.avi\",\n" +
                "      \"mimeType\":\"avi\",\n" +
                "      \"length\":\"128\",\n" +
                "      \"source\":\"https://static.oschina.net/uploads/space/2013/0813/111924_i4x6_179699.jpg\",\n" +
                "      \"ftpUserName\":\"\",\n" +
                "      \"ftpPassword\":\"\",\n" +
                "      \"uploadType\":\"http\"\n" +
                "    }\n" +
                "}",new TransRequest()).orElse(null);
        System.out.println(request);
    }
}
