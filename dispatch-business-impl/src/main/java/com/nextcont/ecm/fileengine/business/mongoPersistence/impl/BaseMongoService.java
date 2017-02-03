package com.nextcont.ecm.fileengine.business.mongoPersistence.impl;

import com.nextcont.ecm.fileengine.business.mongoPersistence.BaseMongoDao;
import com.nextcont.ecm.fileengine.business.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/11
 * Time: 15:53
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseMongoService<T> implements BaseMongoDao<T>{

    private static Logger logger = LoggerFactory.getLogger(BaseMongoService.class);

    private MongoTemplate mongoTemplate;

    public void setMongoTemplate(MongoTemplate proxyMongoTemplate){
        mongoTemplate = proxyMongoTemplate;
        logger.info("proxyMongoTemplate set success.");
    }

    @Override
    public List<T> find(Query query) {
        return mongoTemplate.find(query, this.getEntityClass());
    }

    @Override
    public T findOne(Query query) {
        return mongoTemplate.findOne(query, this.getEntityClass());
    }

    @Override
    public void update(Query query, Update update) {
        mongoTemplate.findAndModify(query, update, this.getEntityClass());
    }

    @Override
    public T save(T entity) {
        mongoTemplate.insert(entity);
        return entity;
    }

    @Override
    public T findById(String id) {
        return mongoTemplate.findById(id, this.getEntityClass());
    }

    @Override
    public T findById(String id, String collectionName) {
        return mongoTemplate.findById(id, this.getEntityClass(), collectionName);
    }

    @Override
    public boolean deleteById(String id){
        try {
            mongoTemplate.remove(query(where("id").is(id)),this.getEntityClass());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Query query) {
        try {
            mongoTemplate.remove(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Page<T> findPage(Page<T> page,Query query){
        long count = this.count(query);
//        page.setTotal(count);
//        int pageNumber = page.getPageNumber();
//        int pageSize = page.getPageSize();
//        query.skip((pageNumber - 1) * pageSize).limit(pageSize);
//        List<T> rows = this.find(query);
//        page.setRows(rows);
        return page;
    }

    @Override
    public long count(Query query){
        return mongoTemplate.count(query, this.getEntityClass());
    }

    /**
     * 获取需要操作的实体类class
     *
     * @return
     */
    private Class<T> getEntityClass(){
        return ReflectionUtils.getSuperClassGenricType(getClass());
    }
}
