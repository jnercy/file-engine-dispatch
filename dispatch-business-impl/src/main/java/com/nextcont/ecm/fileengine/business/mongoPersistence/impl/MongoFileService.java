package com.nextcont.ecm.fileengine.business.mongoPersistence.impl;

import com.nextcont.ecm.fileengine.file.FileAggregation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/12
 * Time: 11:03
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class MongoFileService extends BaseMongoService<FileAggregation> {

    @Resource
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void init(){
        setMongoTemplate(mongoTemplate);
    }
}
