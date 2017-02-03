package com.nextcont.ecm.fileengine.business.persistence.dao;


import com.nextcont.ecm.fileengine.bean.FileRecord;


/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/27
 * Time: 10:13
 * To change this template use File | Settings | File Templates.
 */
public interface FileRecordDao {

    public boolean insert(FileRecord record,String globalId);
}
