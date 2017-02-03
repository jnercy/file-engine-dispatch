package com.nextcont.ecm.fileengine.api;


import com.nextcont.ecm.fileengine.bean.request.CancelRequest;
import com.nextcont.ecm.fileengine.bean.request.QueryRequest;
import com.nextcont.ecm.fileengine.bean.request.TransRequest;
import com.nextcont.ecm.fileengine.bean.response.CancelResponse;
import com.nextcont.ecm.fileengine.bean.response.TransResponse;
import com.nextcont.ecm.fileengine.entity.QueryTransitionRes;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 15:02
 * To change this template use File | Settings | File Templates.
 */
public interface TransitionManager {

    public String upload(HttpServletRequest request) throws IOException;

    public TransResponse transition(TransRequest request);

    public QueryTransitionRes query(QueryRequest request);

    public CancelResponse cancel(CancelRequest request);


}
