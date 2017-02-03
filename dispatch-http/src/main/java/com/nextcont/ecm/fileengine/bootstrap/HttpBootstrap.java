package com.nextcont.ecm.fileengine.bootstrap;

import com.nextcont.ecm.fileengine.file.FileModule;
import com.nextcont.ecm.fileengine.file.FileService;
import com.nextcont.ecm.fileengine.http.HttpModule;
import com.nextcont.ecm.fileengine.http.HttpServer;
import com.nextcont.ecm.fileengine.persistence.manager.DatabaseModule;
import com.nextcont.ecm.fileengine.persistence.manager.FileRecordManager;
import com.nextcont.ecm.fileengine.setting.Settings;
import com.nextcont.ecm.fileengine.setting.SettingsBuilder;
import com.nextcont.ecm.fileengine.setting.SettingsModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 启动帮助类
 * Created by wangxudong on 14-8-13.
 */
public class HttpBootstrap {

    private static Logger logger = LoggerFactory.getLogger("bootstrap");

    private static volatile Thread keepAliveThread;
    private static volatile CountDownLatch keepAliveLatch;

    private Injector injector;

    private void setUp() throws IOException {
        Settings settings = SettingsBuilder.newBuilder().configForName("file-server.yaml").builder();
        logger.info("config from {},it has been loaded!",settings.configName());
        Module settingModule = new SettingsModule(settings);
        Module httpModule = new HttpModule();
        Module fileModule = new FileModule();
        Module databaseModule = new DatabaseModule();
        this.injector = Guice.createInjector(settingModule,httpModule,fileModule,databaseModule);
        logger.info("modules injection of finished!");
    }

    private void start() throws Exception{
        injector.getInstance(FileService.class).start();
        logger.info("file module started!");
        injector.getInstance(FileRecordManager.class).start();
        logger.info("database module started!");
        injector.getInstance(HttpServer.class).start();
        logger.info("http module started!");

    }

    private void stop() throws Exception{
        injector.getInstance(HttpServer.class).stop();
        logger.info("http module stop!");
        injector.getInstance(FileService.class).stop();
        logger.info("file module stop!");
        injector.getInstance(FileRecordManager.class).start();
        logger.info("database module stop!");
    }

    public static void main(String[] args) {

        try {

            final HttpBootstrap httpBootstrap = new HttpBootstrap();
            httpBootstrap.setUp();
            httpBootstrap.start();

            logger.info("keepAlive");

            keepAliveLatch = new CountDownLatch(1);
            // keep this thread alive (non daemon thread) until we shutdown
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    try {
                        httpBootstrap.stop();
                    }catch (Exception e){
                        logger.error("stop fail!",e);
                    }finally {
                        keepAliveLatch.countDown();
                    }
                }
            });
            keepAliveThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        keepAliveLatch.await();
                    } catch (InterruptedException e) {
                        // bail out
                    }
                }
            }, "file-http-server[keepAlive/0.1.0]");
            keepAliveThread.setDaemon(false);
            keepAliveThread.start();
        } catch (Exception e) {
            logger.error("system start fail!",e);
            System.exit(3);
        }

    }


}
