import com.nextcont.ecm.fileengine.business.impl.FastfdsFileManagerImpl;
import com.squareup.okhttp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/10/12
 * Time: 17:17
 * To change this template use File | Settings | File Templates.
 */

public class TestUpload {

    private static final Logger logger = LoggerFactory.getLogger(FastfdsFileManagerImpl.class);

    private static String dfsServerHost = "http://192.168.15.203:9200";

//    private static String dfsServerHost = "http://127.0.0.1:9200";

    private static String IMGUR_CLIENT_ID = "...";

    private static OkHttpClient client;


    public static void main(String[] args) {
        client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setWriteTimeout(300, TimeUnit.SECONDS);
        client.setReadTimeout(300, TimeUnit.SECONDS);
        File file = new File("D://demo.jpg");

        upload(file);
    }

    public static String upload(File file) {

        String uploadResult =  "";

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new TestUpload.MyX509TrustManager()}, new java.security.SecureRandom());
            client.setSslSocketFactory(sslContext.getSocketFactory());
            client.setHostnameVerifier(new TestUpload.MyHostnameVerifier());
            uploadResult = upload2http(file);
        } catch (NoSuchAlgorithmException | KeyManagementException | IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return uploadResult;
    }

    private static String upload2http(File file) throws IOException {

        String uploadUrl = dfsServerHost+"/file/upload";

        MediaType mediaType = MediaType.parse(contentType(file.getAbsolutePath()));

        RequestBody requestBody = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"title\""),
                        RequestBody.create(null, "Square Logo"))
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"image\";filename=\""+file.getName()+"\""),
                        RequestBody.create(mediaType, file))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .header("Connection", "Close")
                .url(uploadUrl)
                .post(requestBody)
                .build();

        com.squareup.okhttp.Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        String uploadResult = response.body().string();
        logger.info(uploadResult);

        return uploadResult;
    }

    private static String contentType(String path) {
        if (path.endsWith(".png")) return "image/png";
        if (path.endsWith(".jpg")) return "image/jpeg";
        if (path.endsWith(".jpeg")) return "image/jpeg";
        if (path.endsWith(".gif")) return "image/gif";
        if (path.endsWith(".html")) return "text/html; charset=utf-8";
        if (path.endsWith(".txt")) return "text/plain; charset=utf-8";

        return "application/octet-stream";
    }

    static class MyX509TrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // Log.d(tag, "check client trusted. authType=" + authType);
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // Log.d(tag, "check servlet trusted. authType=" + authType);
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            // Log.d(tag, "get acceptedissuer");
            return null;
        }

    }

    static class MyHostnameVerifier implements HostnameVerifier {

        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}
