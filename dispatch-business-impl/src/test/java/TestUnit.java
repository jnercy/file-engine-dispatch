import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/12/30
 * Time: 11:05
 * To change this template use File | Settings | File Templates.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUnit {

    private static final Logger LOG = Logger.getLogger(TestUnit.class);

    @Test
    public void testFirst() throws Exception {
        System.out.println(1);
        LOG.info("------1--------");
    }

    @Test
    public void testSecond() throws Exception {
        System.out.println(2);
        LOG.info("------2--------");

    }

    @Test
    public void testThird() throws Exception {
        System.out.println(3);
        LOG.info("------3--------");
    }
}
