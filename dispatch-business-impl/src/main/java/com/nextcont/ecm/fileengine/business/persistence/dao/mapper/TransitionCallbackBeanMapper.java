package com.nextcont.ecm.fileengine.business.persistence.dao.mapper;

import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionCallbackBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TransitionCallbackBeanMapper {
    int insert(TransitionCallbackBean record);

    int insertSelective(TransitionCallbackBean record);

    @Select("select * from transition_callback where globalId = #{id}")
    List<TransitionCallbackBean> selectByGlobalId(String id);

}