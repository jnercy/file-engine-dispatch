import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/9
 * Time: 13:18
 * To change this template use File | Settings | File Templates.
 */
public class TestFileMd5 {

    public static void main(String[] args) throws IOException {

        FileInputStream stream = new FileInputStream("D://demo.txt");
        String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(stream));
        IOUtils.closeQuietly(stream);
        System.out.println(md5);
    }
}
