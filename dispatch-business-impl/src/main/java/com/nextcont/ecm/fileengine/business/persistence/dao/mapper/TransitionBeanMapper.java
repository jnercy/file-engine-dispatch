package com.nextcont.ecm.fileengine.business.persistence.dao.mapper;

import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionBean;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository(value = "transitionMapper")
public interface TransitionBeanMapper {
    int deleteByPrimaryKey(String globalId);

    int insert(TransitionBean record);

    int insertSelective(TransitionBean record);

    TransitionBean selectByPrimaryKey(String globalId);

    int updateByPrimaryKeySelective(TransitionBean record);

    int updateByPrimaryKey(TransitionBean record);

    @Select("SELECT * FROM transition")
    List<TransitionBean> getUsers();

    @Select("select * from transition where globalId = #{id}")
    TransitionBean selectByGlobalId(String id);
}