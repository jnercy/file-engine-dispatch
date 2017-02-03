import com.nextcont.ecm.fileengine.business.utils.DownloadUtils;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/10/28
 * Time: 10:12
 * To change this template use File | Settings | File Templates.
 */
public class TestFileDownload {

    static ExecutorService pool = null;

    static CountDownLatch cut = null;

    static Integer TASKCOUNT = 400;

    static Integer THREADCOUNT = 8;





    public static void main(String[] args) throws InterruptedException {

        cut = new CountDownLatch(TASKCOUNT);
        pool = Executors.newFixedThreadPool(THREADCOUNT);
        Integer [] count = new Integer[]{0};
        for(int i = 0 ; i< TASKCOUNT ; i++) {
            pool.execute(()->{
                try {
                    long time = System.currentTimeMillis();
                    File downloadFile =DownloadUtils.downLoadFromUrl("http://192.168.15.205:9200/group1/M00/00/0B/wKgPzVgG5F-AHl1jAAI4UgcLt6c101.gif", "D://temp//"+count[0]);
                    System.out.println(System.currentTimeMillis()-time);
                    count[0]++;
//                    downloadFile.delete();
                    cut.countDown();

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            });
            System.out.println("number:"+i);
        }

        cut.await();

        System.out.println("task end!");


    }
}
