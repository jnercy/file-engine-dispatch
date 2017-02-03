package com.nextcont.ecm.fileengine.business.persistence.dao.impl;

import com.nextcont.ecm.fileengine.bean.request.TransRequest;
import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionBean;
import com.nextcont.ecm.fileengine.business.persistence.dao.TransitionDao;
import com.nextcont.ecm.fileengine.business.persistence.dao.mapper.TransitionBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/22
 * Time: 10:29
 * To change this template use File | Settings | File Templates.
 */
@Service("transitionService")
public class TransitionBeanImpl implements TransitionDao{

    @Autowired
    private TransitionBeanMapper transitionMapper;


    @Override
    public List<TransitionBean> getAll() {
        return transitionMapper.getUsers();
    }

    @Override
    public boolean insert(TransRequest tr) {
        TransitionBean.TransitionBeanBuilder recordBuild = TransitionBean.builder();
        TransitionBean record =
                recordBuild
                .globalId(tr.globalId)
                .createdate(new Date())
                .filename(tr.getData().getFileName())
                .length(tr.getData().getLength())
                .mimetype(tr.getData().getMimeType())
                .source(tr.getData().getSource())
                .callbackurl(tr.getCallbackData().getCallbackUrl())
                .callbacktype(tr.getCallbackData().getCallbackType())
                .websocketsessionid(tr.getCallbackData().getWebsocketSessionId())
                .filesize(new Long(tr.getData().getFileSize()).intValue())
                .build();
        int resultCount = transitionMapper.insert(record);
        return resultCount>0;
    }

    @Override
    public TransitionBean selectByGlobalId(String id) {
        return transitionMapper.selectByGlobalId(id);
    }


}
