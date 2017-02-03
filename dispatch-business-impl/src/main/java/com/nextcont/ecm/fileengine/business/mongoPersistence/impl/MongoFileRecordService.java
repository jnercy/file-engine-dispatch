package com.nextcont.ecm.fileengine.business.mongoPersistence.impl;

import com.nextcont.ecm.fileengine.file.FileProcessRecord;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/12
 * Time: 16:08
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class MongoFileRecordService extends BaseMongoService<FileProcessRecord>{

    @Resource
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void init(){
        setMongoTemplate(mongoTemplate);
    }

}
