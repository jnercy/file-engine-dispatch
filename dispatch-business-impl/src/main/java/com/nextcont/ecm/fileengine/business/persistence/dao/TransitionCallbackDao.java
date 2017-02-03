package com.nextcont.ecm.fileengine.business.persistence.dao;

import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionCallbackBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/10/11
 * Time: 11:24
 * To change this template use File | Settings | File Templates.
 */
public interface TransitionCallbackDao {

    int insertSelective(TransitionCallbackBean record);

    List<TransitionCallbackBean> selectByGlobalId(String id);
}
