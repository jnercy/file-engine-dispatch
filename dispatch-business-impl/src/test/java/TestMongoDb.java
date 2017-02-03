import com.nextcont.ecm.fileengine.business.mongoPersistence.impl.BaseMongoService;
import com.nextcont.ecm.fileengine.file.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/11
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:mybatis-config.xml",
        "classpath:spring-mongodb.xml",
        "classpath:spring-data.xml"}
)
public class TestMongoDb {


    @Autowired
    public BaseMongoService<FileAggregation> mongoFileService;

    @Autowired
    public BaseMongoService<FileProcessRecord> mongoFileRecordService;

    @Autowired
    public BaseMongoService<FileMetaData> mongoFileMetadataService;

    private final long INITIALVERSION = 1L;

    @Test
    public void testQueryById(){
        FileAggregation file = mongoFileService.findById("58787dad1c03efef8ee22749");
        System.out.println(file.toString());
    }

    @Test
    public void testQuery(){
        List<FileAggregation> file = mongoFileService.find(query(where("owners.emailAddress").is("jnercywang@gmail.com")));
        System.out.println(file.toString());
    }

    @Test
    public void testDelete(){
        mongoFileService.deleteById("5876fa2c1c03afd663fc743e");
    }

    @Test
    public void testUpdate(){
        FileOwners.FileOwnersBuilder ownersBuilder = FileOwners.builder();
        FileOwners owners = ownersBuilder
                .displayName("wang Jnercy")
                .photoLink("https://lh4.googleusercontent.com/-QL4nj5VHT7E/AAAAAAAAAAI/AAAAAAAAAAo/vP5Ue1hGfpw/s64/photo.jpg")
                .permissionId("123123")
                .emailAddress("jnercywang@gmail.com")
                .build();

        mongoFileService.update(
                query(where
                        ("name").is("google doc").and("fileId").is("1cgwWlTGgn0LsqJSKMjFnjParsp0TcV2qdMcNqAgETlw")),
                new Update().set("owners",owners)
        );
    }

    @Test
    public void testDeleteByQuery(){
        boolean status = mongoFileService.delete(query(where("owners.emailAddress").is("jnercywang@gmail.com")));
        System.out.println(status);
    }

    @Test
    public void testInsertMetadata(){
        FileMetaData.FileMetaDataBuilder builder = FileMetaData.builder();
        FileMetaData metaData =
                builder
                .fileId("e79963e6-2a7d-4cf5-8eeb-a00c327749ef")
                .contentHints(new ContentHints("normal", new Thumbnail("www.baidu.com","image/jpg")))
                .description("init metata!!")
                .mimeType("image/jpg")
                .modifiedTime(new Date())
                .folderColorRgb("normal")
                .build();

        mongoFileMetadataService.save(metaData);

    }

    @Test
    public void testQueryMetadata(){
        List<FileMetaData> file = mongoFileMetadataService.find(query(where("fileId").is("e79963e6-2a7d-4cf5-8eeb-a00c327749ef")));
        System.out.println(file.toString());
    }

    @Test
    public void testInsertRecord(){

    }


    @Test
    public void testInsert(){
        for(int i =0; i<50; i++){
            String fileId = UUID.randomUUID().toString();
            makeFileAggregation(fileId);
            makeFileRecord(fileId);
        }
    }

    public void makeFileRecord(String fileId){
        FileProcessRecord.FileProcessRecordBuilder builder = FileProcessRecord.builder();

        mongoFileRecordService.save( builder
                .fileId(fileId)
                .userId("jnercywang@gmail.com")
                .name("google doc")
                .ownedByMe(true)
                .modifyByMe(true)
                .modifyByMeTime(new Date())
                .viewedByMe(false)
                .viewByMeTime(new Date())
                .createTime(new Date())
                .folder(false)
                .quotaBytesUsed(1890384L)
                .recency(true)
                .sharedWithMeTime(new Date())
                .starred(true)
                .build());
    }

    public void makeFileAggregation(String fileId){
        FileOwners.FileOwnersBuilder ownersBuilder = FileOwners.builder();
        FileOwners owners = ownersBuilder
                .displayName("wang Jnercy")
                .photoLink("https://lh4.googleusercontent.com/-QL4nj5VHT7E/AAAAAAAAAAI/AAAAAAAAAAo/vP5Ue1hGfpw/s64/photo.jpg")
                .permissionId("14534550892102641987")
                .emailAddress("jnercywang@gmail.com")
                .build();
        List<FileOwners> ownersArrayList = new ArrayList<>();
        ownersArrayList.add(owners);

        FileCapability.FileCapabilityBuilder capabilityBuilder = FileCapability.builder();
        FileCapability capability = capabilityBuilder
                .canComment(true)
                .canCopy(true)
                .canEdit(true)
                .canShare(true)
                .canReadRevisions(true)
                .build();
        List<FileCapability> fileCapabilities = new ArrayList<>();
        fileCapabilities.add(capability);

        FilePermission.FilePermissionBuilder permissionBuilder = FilePermission.builder();
        FilePermission permission = permissionBuilder
                .id(fileId)
                .type("user")
                .emailAdddress("jnercywang@gmail.com")
                .role("owner")
                .displayName("wang Jnercy")
                .photoLink("https://lh4.googleusercontent.com/-QL4nj5VHT7E/AAAAAAAAAAI/AAAAAAAAAAo/vP5Ue1hGfpw/s64/photo.jpg")
                .build();

        List<FilePermission> permissions = new ArrayList<>();
        permissions.add(permission);
        List<String> parents = new ArrayList<>();
        parents.add("0AGUv084dR5bdUk9PVA");

        List<String> space = new ArrayList<>();
        space.add("drive");

        FileAggregation.FileAggregationBuilder builder = FileAggregation.builder();
        FileAggregation fileAgg = builder
                .fileId(fileId)
                .name("google doc")
                .mimeType("application/vnd.google-apps.document")
                .starred(true)
                .trashed(false)
                .explicitlyTrashed(false)
                .parents(parents)
                .space(space)
                .version(INITIALVERSION)
                .webViewLink("https://docs.google.com/document/d/1cgwWlTGgn0LsqJSKMjFnjParsp0TcV2qdMcNqAgETlw/edit?usp=drivesdk")
                .iconLink("https://ssl.gstatic.com/docs/doclist/images/icon_11_document_list.png")
                .hasThumbnail(true)
                .thumbnailLink("https://docs.google.com/feeds/vt?gd=true&id=1cgwWlTGgn0LsqJSKMjFnjParsp0TcV2qdMcNqAgETlw&v=7&s=AMedNnoAAAAAWHX5k_RTZbPpEx2r53iWKIrn1AF6camb&sz=s220")
                .thumbnailVersion(INITIALVERSION)
                .viewedByMeTime(new Date())
                .createdTime(new Date())
                .modifiedTime(new Date())
                .modifiedByMeTime(new Date())
                .modifiedByMe(true)
                .owners(ownersArrayList)
                .lastModifyUser(owners)
                .shared(true)
                .ownerId("jnercywang@gmail.com")
                .capabilities(capability)
                .viewersCanCopyContent(true)
                .writersCanShare(true)
                .permissions(permissions)
                .originalFilename("0.gif")
                .fullFileExtension("gif")
                .fileExtension(".gif")
                .md5Checksum("df0b1d9107dbe1a7a63e62550c1a906d")
                .size(1890384L)
                .quotaBytesUsed(1890384L)
                .headRevisionId("0B2Uv084dR5bdRm0wZFlnaUd3WURyS1d0ckR2M1FFeTFTNXRRPQ")
                .build();


        mongoFileService.save(fileAgg);
        System.out.println(fileAgg.toString());
    }

}
