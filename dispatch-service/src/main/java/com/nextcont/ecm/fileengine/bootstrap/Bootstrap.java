package com.nextcont.ecm.fileengine.bootstrap;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/18
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
public class Bootstrap {
    private static Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    private static volatile CountDownLatch keepAliveLatch;

    private void setUp() throws IOException {

    }

    private void start() throws Exception{
        System.setProperty("dubbo.application.logger", "slf4j");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:spring-fastdfs.xml",
                "classpath:spring-business.xml",
                "classpath:spring-consumer.xml",
                "classpath:mybatis-config.xml",
                "classpath:spring-mongodb.xml",
                "classpath:spring-data.xml",
                "classpath:spring-dubbo.xml"
                );
        context.start();
    }

    private void stop() throws Exception{

    }

    public static void main(String[] args){
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.setUp();
            bootstrap.start();
            logger.info("dispatch-service started!!!");
            keepAliveLatch = new CountDownLatch(1);
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    try {
                        bootstrap.stop();
                    }catch (Exception e){
                        logger.error("stop fail!",e);
                    }finally {
                        keepAliveLatch.countDown();
                    }
                }
            });
            Thread keepAliveThread = new Thread(()->{
                try {
                    keepAliveLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "dispatch-service[keepAlive/" + Version.CURRENT + "]");
            keepAliveThread.setDaemon(false);
            keepAliveThread.start();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("system start fail!",e);
            System.exit(3);
        }
    }
}
