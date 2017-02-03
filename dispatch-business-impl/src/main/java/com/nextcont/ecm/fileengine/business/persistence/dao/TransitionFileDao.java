package com.nextcont.ecm.fileengine.business.persistence.dao;

import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionFilesBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/10/10
 * Time: 13:21
 * To change this template use File | Settings | File Templates.
 */
public interface TransitionFileDao {

    List<TransitionFilesBean> selectByGlobalId(String id);

}
