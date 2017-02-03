package com.nextcont.ecm.fileengine.business.impl;

import com.nextcont.ecm.fileengine.api.TransitionManager;
import com.nextcont.ecm.fileengine.bean.UploadType;
import com.nextcont.ecm.fileengine.bean.request.CancelRequest;
import com.nextcont.ecm.fileengine.bean.request.QueryRequest;
import com.nextcont.ecm.fileengine.bean.request.TransRequest;
import com.nextcont.ecm.fileengine.bean.response.CancelResponse;
import com.nextcont.ecm.fileengine.bean.response.TransResponse;
import com.nextcont.ecm.fileengine.business.event.bus.DfsTransitionEventBus;
import com.nextcont.ecm.fileengine.business.event.bus.RequestEventBus;
import com.nextcont.ecm.fileengine.business.event.bus.TransitionEventBus;
import com.nextcont.ecm.fileengine.business.persistence.bean.TransitionBean;
import com.nextcont.ecm.fileengine.business.persistence.dao.TransitionDao;
import com.nextcont.ecm.fileengine.entity.QueryTransitionRes;
import com.nextcont.ecm.fileengine.entity.ResultMap;
import com.nextcont.ecm.fileengine.video.transcode.api.IConvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
@Path("trans")
@Service("transitionManager")
public class TransitonManagerImpl implements TransitionManager {

    private static Logger logger = LoggerFactory.getLogger(TransitonManagerImpl.class);

    @Autowired
    private IConvertService convertService;

    @Autowired
    private TransitionDao transitionService;

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public String upload(@Context HttpServletRequest request){
//        try {
//            // 判断enctype属性是否为multipart/form-data
//            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//
//            // Create a factory for disk-based file items
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//
//            // 当上传文件太大时，因为虚拟机能使用的内存是有限的，所以此时要通过临时文件来实现上传文件的保存
//            // 此方法是设置是否使用临时文件的临界值（单位：字节）
//            factory.setSizeThreshold(1024*1024*100);
//
//            // 与上一个结合使用，设置临时文件的路径（绝对路径）
////        factory.setRepository(yourTempDirectory);
//
//            // Create a new file upload handler
//            ServletFileUpload upload = new ServletFileUpload(factory);
//
//            // 设置上传内容的大小限制（单位：字节）
////        upload.setSizeMax(yourMaxRequestSize);
//
//            // Parse the request
//            List<?> items = upload.parseRequest(request);
//
//            Iterator iter = items.iterator();
//            while (iter.hasNext()) {
//                FileItem item = (FileItem) iter.next();
//
//                if (item.isFormField()) {
//                    //如果是普通表单字段
//                    String name = item.getFieldName();
//                    String value = item.getString();
//                } else {
//                    //如果是文件字段
//                    String fieldName = item.getFieldName();
//                    String fileName = item.getName();
//                    String contentType = item.getContentType();
//                    boolean isInMemory = item.isInMemory();
//                    long sizeInBytes = item.getSize();
//                    // Process a file upload
//                    File uploadedFile = new File("D://download//demo.txt");
//                    item.write(uploadedFile);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }


    @POST
    @Path("request")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public TransResponse transition(TransRequest request) {

        logger.info("transition start... request: {}", request.toString());

        RequestEventBus.post(request);

        Thread convertProcess = new Thread(()->{
            if(UploadType.getUploadType(request.getData().getUploadType())==UploadType.dfs)
                DfsTransitionEventBus.post(request);
            else
                TransitionEventBus.post(request);
        });

        convertProcess.start();

        return request.convert2Response();
    }

    @POST
    @Path("query")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public QueryTransitionRes query(QueryRequest request) {
        request.setGlobalId(request.getId());
        TransitionBean bean = transitionService.selectByGlobalId(request.getId());
        QueryTransitionRes result = convertService.queryConvertProgress(request);
        result.getTransition().setAlijobid(bean.getAlijobid());

        return result;

    }

    @POST
    @Path("cancel")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public CancelResponse cancel(CancelRequest request) {

        request.setGlobalId(request.getId());

        TransitionBean bean = transitionService.selectByGlobalId(request.getId());

        CancelResponse cancelResponse;

        ResultMap result = null;

        if (request.checkGlobalId())
            result = convertService.cancelConvert(request);

        cancelResponse = result == null || !result.isSucess() ? request.convert2ErrorResponse() : request.convert2Response();

        cancelResponse.setFileName(bean.getFilename());
        cancelResponse.setAction("cancel");
        cancelResponse.setMsg("");


        return cancelResponse;
    }
}
