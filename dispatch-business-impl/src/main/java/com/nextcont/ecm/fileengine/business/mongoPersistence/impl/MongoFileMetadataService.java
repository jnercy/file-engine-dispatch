package com.nextcont.ecm.fileengine.business.mongoPersistence.impl;

import com.nextcont.ecm.fileengine.file.FileMetaData;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/3
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class MongoFileMetadataService extends BaseMongoService<FileMetaData>{

    @Resource
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void init(){
        setMongoTemplate(mongoTemplate);
    }

}
