package com.nextcont.ecm.fileengine.business.persistence.dao.impl;

import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionCallbackBean;
import com.nextcont.ecm.fileengine.business.persistence.dao.TransitionCallbackDao;
import com.nextcont.ecm.fileengine.business.persistence.dao.mapper.TransitionCallbackBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/10/11
 * Time: 11:27
 * To change this template use File | Settings | File Templates.
 */
@Service("transitionCallbackService")
public class TransitionCallbackBeanImpl implements TransitionCallbackDao {

    @Autowired
    TransitionCallbackBeanMapper mapper;


    @Override
    public int insertSelective(TransitionCallbackBean record) {
        return mapper.insertSelective(record);
    }

    @Override
    public List<TransitionCallbackBean> selectByGlobalId(String id) {
        return mapper.selectByGlobalId(id);
    }
}
