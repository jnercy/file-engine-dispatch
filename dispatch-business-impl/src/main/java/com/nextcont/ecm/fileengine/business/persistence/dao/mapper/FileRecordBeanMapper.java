package com.nextcont.ecm.fileengine.business.persistence.dao.mapper;

import com.nextcont.ecm.fileengine.business.persistence.bean.FileRecordBean;
import org.springframework.stereotype.Repository;

@Repository(value = "fileRecordMapper")
public interface FileRecordBeanMapper {

    int deleteByPrimaryKey(String globalId);

    int insert(FileRecordBean record);

    int insertSelective(FileRecordBean record);

    FileRecordBean selectByPrimaryKey(String globalId);

    int updateByPrimaryKeySelective(FileRecordBean record);

    int updateByPrimaryKey(FileRecordBean record);
}