package com.nextcont.ecm.fileengine.setting;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class SettingsLoader {

    private static Logger logger = LoggerFactory.getLogger("setting-loader");

    public static Map<String,String> load(Map<String,String> defaultSettings) throws IOException {
        return load(defaultSettings,"autumn-server.yaml");
    }

    public static Map<String,String> load(Map<String,String> defaultSettings,String configName) throws IOException {

        URL uri = ClassLoader.getSystemClassLoader().getResource(configName);
        checkArgument(uri!=null,"pls!!! check the classpath have %s",configName);
        YAMLFactory yamlFactory = new YAMLFactory();
        YAMLParser yamlParser = yamlFactory.createParser(uri);
        JsonToken token = yamlParser.nextToken();
        Map<String,String> settings = Maps.newHashMap(defaultSettings);
        StringBuilder sb = new StringBuilder();
        List<String> path = new ArrayList<String>();
        if (token == null) {
            return settings;
        }
        checkArgument(token == JsonToken.START_OBJECT,"malformed, expected settings to start with 'object', instead was [%s]",token);
        object(settings,yamlParser,path,sb,null);
        return settings;
    }

    private static void object(Map<String,String> settings,YAMLParser parser,List<String> path,StringBuilder sb,String fieldName) throws IOException {
        if(fieldName !=null){
            path.add(fieldName);
        }
        JsonToken token;
        String currentName="";
        while( (token = parser.nextToken()) != JsonToken.END_OBJECT ){
            if(token == JsonToken.START_OBJECT){
                object(settings,parser,path,sb,currentName);
            }else if (token == JsonToken.START_ARRAY){
                array(settings,parser,path,sb,currentName);
            }else if (token == JsonToken.FIELD_NAME){
                currentName = parser.getCurrentName();
            }else if (token == JsonToken.VALUE_NULL){

            }else{
                value(settings,parser,path,sb,currentName);
            }
        }

        if (fieldName != null) {
            path.remove(path.size() - 1);
        }
    }

    private static void array(Map<String,String> settings,YAMLParser parser,List<String> path,StringBuilder sb,String fieldName) throws IOException {
        JsonToken token;
        int counter = 0;
        while( (token = parser.nextToken()) != JsonToken.END_OBJECT ){
            if(token == JsonToken.START_OBJECT){
                object(settings,parser,path,sb,fieldName+counter);
            }else if (token == JsonToken.START_ARRAY){
                array(settings,parser,path,sb,fieldName+counter);
            }else if (token == JsonToken.FIELD_NAME){
                fieldName = parser.getCurrentName();
            }else if (token == JsonToken.VALUE_NULL){

            }else{
                value(settings,parser,path,sb,fieldName+counter);
            }
        }
    }

    private static void value(Map<String,String> settings,YAMLParser parser,List<String> path,StringBuilder sb,String fieldName) throws IOException {
        sb.setLength(0);
        for (String pathEle : path) {
            sb.append(pathEle).append('.');
        }
        sb.append(fieldName);
        settings.put(sb.toString(), parser.getText());
    }


    public static void main(String[] args) throws IOException {
        SettingsLoader.load(new HashMap<>());
    }

}
