package com.nextcont.ecm.fileengine.business.impl;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.nextcont.ecm.fileengine.api.DriveManager;
import com.nextcont.ecm.fileengine.bean.Try;
import com.nextcont.ecm.fileengine.bean.request.FileListRequest;
import com.nextcont.ecm.fileengine.bean.request.FileLockRequest;
import com.nextcont.ecm.fileengine.bean.request.FileShareRequest;
import com.nextcont.ecm.fileengine.business.mongoPersistence.impl.BaseMongoService;
import com.nextcont.ecm.fileengine.file.FileAggregation;
import com.nextcont.ecm.fileengine.file.FileMetaData;
import com.nextcont.ecm.fileengine.file.FilePermission;
import com.nextcont.ecm.fileengine.file.FileProcessRecord;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/13
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
@Path("/files")
@Service("driveManager")
public class DriveManagerImpl implements DriveManager{


    @Autowired
    public BaseMongoService<FileAggregation> mongoFileService;

    @Autowired
    public BaseMongoService<FileProcessRecord> mongoFileRecordService;

    private final String DEMO_USER_ID = "jnercywang@gmail.com";

    @POST
    @Path("/{fileId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FileAggregation> get(@PathParam("fileId") String fileId){

        List<FileProcessRecord> fileProcessRecords = mongoFileRecordService.find(query(where("fileId").is(fileId).and("userId").is(DEMO_USER_ID)));
        List<FileAggregation> aggregation = fileProcessRecords
                .stream()
                .findFirst()
                .map(record -> mongoFileService.find(query(where("fileId").is(record.getFileId()))))
                .get();

        return aggregation;
    }

    @POST
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<FileAggregation> list(FileListRequest request) {
        List<FileProcessRecord> fileProcessRecords = mongoFileRecordService.find(query(where("userId").is(request.getUserId())).limit(request.getPageSize()));

        BasicDBList fileIdQueryList = new BasicDBList();

        fileProcessRecords
                .forEach(record -> fileIdQueryList.add(new BasicDBObject("fileId",record.getFileId())));

        List<FileAggregation> aggregation = new ArrayList<>();

        if(fileIdQueryList.size()>0) {
            BasicDBObject orDBObject = new BasicDBObject("$or", fileIdQueryList);
            aggregation = mongoFileService.find(new BasicQuery(orDBObject));
            return aggregation;
        }
        else
            return aggregation;
    }

    @POST
    @Path("/{fileId}/lock")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public String lock(FileLockRequest request,@PathParam("fileId") String fileId) {
        try {
            mongoFileRecordService.find(
                    query(
                            where("userId").is(DEMO_USER_ID)
                            .and("fileId").is(fileId)
                            .and("ownedByMe").is(true)
                            .and("isLock").is(false)))
                    .stream()
                    .findFirst()
                    .ifPresent(record -> {
                        Date unlockTime = new DateTime().plusSeconds(request.getQuantity()).toDate();
                        Update update = new Update();
                        update
                                .set("isLock", true)
                                .set("deblockingTime", unlockTime);
                        mongoFileService.update(query(where("fileId").is(fileId)), update);
                    });
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
        return "success";
    }


    //分享逻辑需要梳理
    @POST
    @Path("/{fileId}/sharing")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public String share(FileShareRequest request,@PathParam("fileId") String fileId) {
        mongoFileRecordService.find(query(where( "userId").is(DEMO_USER_ID).and("fileId").is(fileId)))
                .stream()
                .findFirst()
                .ifPresent(record -> mongoFileService.find(query(where("field").is(fileId)))
                        .stream()
                        .findFirst()
                        .ifPresent(fileAggregation ->
                            Try.tried(fileAggregation,agg-> {
                                List<FilePermission> permissions = agg.getPermissions();
                                FilePermission.FilePermissionBuilder builder = FilePermission.builder();
                                FilePermission permission = builder
                                        .id(DEMO_USER_ID)
                                        .type("user")
                                        .emailAdddress(request.getEmailAddress())
                                        .photoLink("")
                                        .displayName("")
                                        .role("")
                                        .build();
                                permissions.add(permission);
                                Update update = new Update();
                                update
                                        .set("permissions", permissions)
                                        .inc("version",1);
                                mongoFileService.update(query(where("fileId").is(fileId)), update);
                            }
                        )));
        return "success";
    }


    //metadata获取
    @GET
    @Path("/{fileId}/metadata")
    @Produces(MediaType.TEXT_HTML)
    public FileMetaData getMetadata(){

        return null;
    }

    //metadata修改
    @POST
    @Path("/{filedId}/metadata")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public FileMetaData modifyMetadata(){
        return null;
    }

}
