package com.nextcont.ecm.fileengine.business.persistence.dao.impl;

import com.nextcont.ecm.fileengine.bean.FileRecord;
import com.nextcont.ecm.fileengine.business.persistence.bean.FileRecordBean;
import com.nextcont.ecm.fileengine.business.persistence.dao.FileRecordDao;
import com.nextcont.ecm.fileengine.business.persistence.dao.mapper.FileRecordBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/27
 * Time: 10:17
 * To change this template use File | Settings | File Templates.
 */
@Service("fileRecordService")
public class FileRecordBeanImpl implements FileRecordDao {


    @Autowired
    private FileRecordBeanMapper fileRecordMapper;

    @Override
    public boolean insert(FileRecord record, String globalId) {

        FileRecordBean.FileRecordBeanBuilder builder = FileRecordBean.builder();

        FileRecordBean insertBean = builder
                .fid(record.getFid())
                .groupName(record.getGroupName())
                .fileName(record.getFileName())
                .extensionsName(record.getExtensionsName())
                .createTime(record.getCreateTime())
                .updateTime(record.getUpdateTime())
                .httpEncoding(record.getHttpEncoding())
                .fsRawValue(record.getFsRawValue())
                .path(record.getPath())
                .globalId(globalId)
                .build();

        return fileRecordMapper.insert(insertBean)>0;
    }
}
