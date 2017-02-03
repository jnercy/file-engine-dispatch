import com.nextcont.ecm.fileengine.FileUtil;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/10/24
 * Time: 13:28
 * To change this template use File | Settings | File Templates.
 */
public class FileTest {

    public static void main(String[] args) throws IOException, InterruptedException {

            System.out.println(FileUtil.currentWorkDir);


            System.out.println("6m.avi".split("\\.")[0]);
//
//            StringBuilder sb = new StringBuilder();
//
//            long originFileSize = 1024 * 1024 * 45;// 45M
            int blockFileSize = 1024 * 1024 * 50;// 50M
//
//            // 生成一个大文件
//            for (int i = 0; i < originFileSize; i++) {
//                sb.append("A");
//            }
//
//            String fileName = FileUtil.currentWorkDir + "origin.myfile";
//            System.out.println(fileName);
//            System.out.println(FileUtil.write(fileName, sb.toString()));
//
//            // 追加内容
//            sb.setLength(0);
//            sb.append("0123456789");
//            FileUtil.append(fileName, sb.toString());

//            FileUtil fileUtil = new FileUtil();
//
//            // 将origin.myfile拆分
//            fileUtil.splitBySize("D://download//5m.avi", blockFileSize);
//
//            Thread.sleep(10000);// 稍等10秒，等前面的小文件全都写完
//
////            // 合并成新文件
//            fileUtil.mergePartFiles(FileUtil.currentWorkDir, ".part",
//                    blockFileSize, FileUtil.currentWorkDir + "new.avi");

        }
}
