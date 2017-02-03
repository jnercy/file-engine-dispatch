package com.nextcont.ecm.fileengine.persistence.manager;

import com.google.inject.Inject;
import com.jolbox.bonecp.BoneCPDataSource;
import com.nextcont.ecm.fileengine.bean.FileRecord;
import com.nextcont.ecm.fileengine.bean.MeqaUpload;
import com.nextcont.ecm.fileengine.common.AbstractLifeCycleComponent;
import com.nextcont.ecm.fileengine.common.LifeCycleComponent;
import com.nextcont.ecm.fileengine.persistence.jooq.Tables;
import com.nextcont.ecm.fileengine.setting.Settings;
import com.nextcont.ecm.fileengine.util.DateTimeUtils;
import org.jooq.*;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultDSLContext;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class FileRecordManager extends AbstractLifeCycleComponent implements LifeCycleComponent {

    private DSLContext dsl;

    @Inject
    public FileRecordManager(Settings settings) {

        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(settings.get("jdbc.driverClass"));
        dataSource.setJdbcUrl(settings.get("jdbc.url"));
        dataSource.setUsername(settings.get("jdbc.username"));
        dataSource.setPassword(settings.get("jdbc.password"));
        ConnectionProvider connectionProvider = new DataSourceConnectionProvider(dataSource);
        dsl = new DefaultDSLContext(connectionProvider, SQLDialect.MYSQL);

    }

    public FileRecord findById(String id){
        FileRecord result = dsl
                .select()
                .from(Tables.FASTDFS_FILERECORD)
                .where(Tables.FASTDFS_FILERECORD.GLOBALID.equal(id))
                .fetchOneInto(FileRecord.class);
        if(result == null)
            throw new DataAccessException(String.format("%s in file records not found!",id));
        return result;
    }

    public MeqaUpload findMeqaInfoById(String id){
        MeqaUpload result = dsl
                .select()
                .from(Tables.MEQAUPLOAD)
                .where(Tables.MEQAUPLOAD.GLOBALID.equal(id))
                .fetchOneInto(MeqaUpload.class);
        if(result == null)
            throw new DataAccessException(String.format("%s in meqa records not found!",id));
        return result;
    }

    public Integer incrementCurrentSize(String id){
        int updateCount =
                dsl.update(Tables.MEQAUPLOAD)
                    .set(Tables.MEQAUPLOAD.CURRENTSHARD,Tables.MEQAUPLOAD.CURRENTSHARD.add(1))
                    .where(Tables.MEQAUPLOAD.GLOBALID.equal(id).and(Tables.MEQAUPLOAD.CURRENTSHARD.lt(Tables.MEQAUPLOAD.SHARDSIZE)))
                    .execute();
        return updateCount;
    }

    public boolean insertFileRecord(FileRecord fileRecord){
        int result = dsl.insertInto(Tables.FASTDFS_FILERECORD)
                .values(fileRecord.getGlobalId(),
                        fileRecord.getFid(),
                        fileRecord.getGroupName(),
                        fileRecord.getFileName(),
                        fileRecord.getExtensionsName(),
                        DateTimeUtils.now(false),
                        DateTimeUtils.now(false),
                        fileRecord.getHttpEncoding(),
                        fileRecord.getFsRawValue(),
                        fileRecord.getPath(),
                        "normal")
                .execute();
        return result>0;
    }

    public boolean insertMeqaUpload(MeqaUpload record){
        int result = dsl.insertInto(Tables.MEQAUPLOAD)
                .values(record.getGlobalId(),
                        record.getFileName(),
                        record.getMimeType(),
                        record.getContentLength(),
                        record.getShardSize(),
                        DateTimeUtils.now(false),
                        DateTimeUtils.now(false),
                        0,
                        record.getPartFileSize()
                       )
                .execute();
        return result>0;
    }

    @Override
    protected void doStart() throws Exception {

    }

    @Override
    protected void doStop() throws Exception {
        dsl.close();
    }
}
