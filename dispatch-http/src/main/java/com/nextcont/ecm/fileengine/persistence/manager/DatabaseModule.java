package com.nextcont.ecm.fileengine.persistence.manager;

import com.google.inject.AbstractModule;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(FileRecordManager.class).asEagerSingleton();

    }
}
