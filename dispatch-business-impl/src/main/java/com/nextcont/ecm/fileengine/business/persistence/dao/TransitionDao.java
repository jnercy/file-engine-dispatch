package com.nextcont.ecm.fileengine.business.persistence.dao;

import com.nextcont.ecm.fileengine.bean.request.TransRequest;
import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/22
 * Time: 10:34
 * To change this template use File | Settings | File Templates.
 */
public interface TransitionDao {

    public List<TransitionBean> getAll();

    public boolean insert(TransRequest tr);

    public TransitionBean selectByGlobalId(String id);

}
