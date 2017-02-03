package com.nextcont.ecm.fileengine.business.persistence.dao.mapper;

import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionFilesBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TransitionFilesBeanMapper {
    int insert(TransitionFilesBean record);

    int insertSelective(TransitionFilesBean record);

    @Select("select * from transition_file where globalId = #{id}")
    List<TransitionFilesBean> selectByGlobalId(String id);
}