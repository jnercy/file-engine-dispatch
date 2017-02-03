package com.nextcont.ecm.fileengine.business.event.transiton;

import com.google.common.eventbus.Subscribe;
import com.nextcont.ecm.fileengine.bean.request.TransRequest;
import com.nextcont.ecm.fileengine.video.transcode.api.IConvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/29
 * Time: 13:07
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ConvertEvent {

    private static Logger logger = LoggerFactory.getLogger(ConvertEvent.class);

    @Autowired
    private IConvertService convertService;

    @Subscribe
    public void convert(TransRequest request){
        logger.info("convert File start ...");

        convertService.convert(request);

        logger.info("convert request committed ...");
        logger.info("transition end  ...");
    }
}
