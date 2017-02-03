package com.nextcont.ecm.fileengine.setting;

import com.google.inject.AbstractModule;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class SettingsModule extends AbstractModule {

    private Settings settings;

    public SettingsModule(Settings settings){
        this.settings = settings;
    }

    @Override
    protected void configure() {
        bind(Settings.class).toInstance(this.settings);
    }
}
