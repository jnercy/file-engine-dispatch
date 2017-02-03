package com.nextcont.ecm.fileengine.setting;


import java.util.Map;

import static com.google.common.base.Preconditions.checkState;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class Settings {

    private Map<String, String> settings;

    private String configName;

    public Settings(Map<String,String> settings){
        this.settings = settings;
    }

    public String get(String key){
        return settings.get(key);
    }

    public String get(String key,String dfValue){
        if(settings.containsKey(key))
            return settings.get(key);
        else
            return dfValue;
    }

    public Integer getAsInt(String key,Integer dfValue){
        if(settings.containsKey(key)){
            String strValue = settings.get(key);
            return Integer.valueOf(strValue);
        }else{
            return dfValue;
        }
    }

    public Float getAsFloat(String key,Float dfValue){
        if(settings.containsKey(key)){
            String strValue = settings.get(key);
            return Float.valueOf(strValue);
        }else{
            return dfValue;
        }
    }

    public Double getAsDouble(String key,Double dfValue){
        if(settings.containsKey(key)){
            String strValue = settings.get(key);
            return Double.valueOf(strValue);
        }else{
            return dfValue;
        }
    }

    public Boolean getAsBoolean(String key,Boolean dfValue){
        if(settings.containsKey(key)){
            String strValue = settings.get(key);
            return Boolean.valueOf(strValue);
        }else{
            return dfValue;
        }
    }

    public void configNameOf(String configName){
        checkState(this.configName==null,"configName already exists");
        this.configName = configName;
    }

    public String configName(){
        return this.configName;
    }

}
