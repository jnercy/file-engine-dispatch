package com.nextcont.ecm.fileengine.setting;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkState;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class SettingsBuilder {

    private String configName;

    public static Map<String,String> EMPTY_SETTINGS(){
        return new HashMap<>(0);
    }


    public SettingsBuilder configForName(String name){
        checkState(configName==null,"configName already exists");
        this.configName = name;
        return this;
    }

    public Settings builder() throws IOException {
        Map<String,String> settings;
        if(this.configName==null)
            settings = SettingsLoader.load(EMPTY_SETTINGS());
        else
            settings = SettingsLoader.load(EMPTY_SETTINGS(),this.configName);
        Settings result = new Settings(settings);
        result.configNameOf(configName);
        return result;
    }

    public static SettingsBuilder newBuilder(){
        return new SettingsBuilder();
    }

}
