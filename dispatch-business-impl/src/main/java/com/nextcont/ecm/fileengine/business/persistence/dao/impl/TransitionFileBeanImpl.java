package com.nextcont.ecm.fileengine.business.persistence.dao.impl;

import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionFilesBean;
import com.nextcont.ecm.fileengine.business.persistence.dao.TransitionFileDao;
import com.nextcont.ecm.fileengine.business.persistence.dao.mapper.TransitionFilesBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/10/10
 * Time: 13:28
 * To change this template use File | Settings | File Templates.
 */
@Service("transitionFileService")
public class TransitionFileBeanImpl implements TransitionFileDao {

    @Autowired
    private TransitionFilesBeanMapper transitionMapper;


    @Override
    public List<TransitionFilesBean> selectByGlobalId(String id) {
        return transitionMapper.selectByGlobalId(id);
    }
}
