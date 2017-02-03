package com.nextcont.ecm.fileengine.http;

import com.google.inject.AbstractModule;
import com.nextcont.ecm.fileengine.http.nettyServer.HttpUploadServer;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class HttpModule  extends AbstractModule {

    @Override
    protected void configure() {

        bind(HttpServer.class).to(HttpUploadServer.class).asEagerSingleton();
    }
}
