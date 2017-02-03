/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.nextcont.ecm.fileengine.http.nettyServer;

import com.nextcont.ecm.fileengine.FileUtil;
import com.nextcont.ecm.fileengine.bean.FileRecord;
import com.nextcont.ecm.fileengine.bean.MeqaUpload;
import com.nextcont.ecm.fileengine.bean.response.Response;
import com.nextcont.ecm.fileengine.file.FileService;
import com.nextcont.ecm.fileengine.persistence.manager.FileRecordManager;
import com.nextcont.ecm.fileengine.util.JsonFormat;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.handler.codec.http.cookie.ServerCookieEncoder;
import io.netty.handler.codec.http.multipart.*;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.EndOfDataDecoderException;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.ErrorDataDecoderException;
import io.netty.handler.codec.http.multipart.InterfaceHttpData.HttpDataType;
import io.netty.util.CharsetUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static io.netty.buffer.Unpooled.copiedBuffer;
import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_MODIFIED;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpUploadServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    private static final Log logger = LogFactory.getLog(HttpUploadServerHandler.class);

    private HttpRequest request;

    private final StringBuilder responseContent = new StringBuilder();

    private static final HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE); // Disk if size exceed

    private FileService fileService;

    private FileRecordManager fileRecordManager;

    public HttpUploadServerHandler(FileService fileService,FileRecordManager fileRecordManager) {
        this.fileService = fileService;
        this.fileRecordManager = fileRecordManager;
    }

    private HttpPostRequestDecoder decoder;

    static {
        DiskFileUpload.deleteOnExitTemporaryFile = true;
        // should delete file on exit (in normal exit)
        DiskFileUpload.baseDirectory = null;
        // system temp directory
        DiskAttribute.deleteOnExitTemporaryFile = true;
        // should delete file on exit (in normal exit)
        DiskAttribute.baseDirectory = null;
        // system temp directory
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        logger.info("channelRegistered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        if (decoder != null) {
            decoder.cleanFiles();
        }

        logger.info("channelUnregistered");
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, HttpObject httpObject) throws Exception {

        if (httpObject instanceof HttpRequest) {

            HttpRequest httpRequest = (HttpRequest) httpObject;
            doHttpRequest(ctx, httpRequest);

        } else if (httpObject instanceof HttpContent) {

            HttpContent httpContent = (HttpContent) httpObject;
            doHttpContent(ctx, httpContent);

        } else {
            logger.error("Unknown http object");
        }

    }

    private void doGet(ChannelHandlerContext ctx, HttpRequest httpRequest) throws URISyntaxException {

        String uri = new URI(request.getUri()).getPath();

        if(uri.equals("/ping")){
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(CONTENT));
            response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }
    }

    private void doPost(ChannelHandlerContext ctx, HttpRequest httpRequest) throws URISyntaxException {
        try {
            decoder = new HttpPostRequestDecoder(factory, httpRequest);
        } catch (ErrorDataDecoderException e) {
            e.printStackTrace();
            responseContent.append(e.getMessage());
            writeResponse(ctx.channel());
            ctx.channel().close();
        }
    }

    private void doHttpRequest(ChannelHandlerContext ctx, HttpRequest httpRequest) throws URISyntaxException {
        HttpRequest request = this.request = httpRequest;
        HttpMethod httpMethod = request.getMethod();

        if (httpMethod.equals(HttpMethod.GET)) {
            doGet(ctx, request);

        } else if (httpMethod.equals(HttpMethod.POST)) {
            doPost(ctx, request);

        } else {
            responseContent.setLength(0);
            responseContent.append(httpMethod.name()).append(" method not support!");
            writeResponse(ctx.channel());
            logger.error(responseContent.toString());
        }
    }

    private void receivceContent(ChannelHandlerContext ctx, HttpContent httpContent,String globalId,boolean isChunked){
        if (decoder != null) {
            // New chunk is received
            try {
                decoder.offer(httpContent);
            } catch (ErrorDataDecoderException e1) {
                e1.printStackTrace();
                responseContent.append(e1.getMessage());
                writeResponse(ctx.channel());
                ctx.channel().close();
                return;
            }
//            responseContent.append('o');
            // example of reading chunk by chunk (minimize memory usage due to Factory)

            String resultInfo = readHttpDataChunkByChunk(globalId,isChunked);

//            logger.info("readHttpDataChunkByChunk:"+DateTimeUtils.now(false));

            // example of reading only if at the end
            if (httpContent instanceof LastHttpContent) {
                responseContent.setLength(0);
                responseContent.append(resultInfo);
                writeResponse(ctx.channel());
                reset();
            }
        } else
            writeResponse(ctx.channel());
    }

    private void doHttpContent(ChannelHandlerContext ctx, HttpContent httpContent) throws URISyntaxException {

        String uri = new URI(request.getUri()).getPath();

        if (uri.startsWith("/file/upload/")) {

            String globalId = uri.replace("/file/upload/","").trim();

            receivceContent(ctx,httpContent,globalId,false);

        }
        else if(uri.startsWith("/file/megaupload/")){

            String globalId = uri.replace("/file/megaupload/","").trim();

            receivceContent(ctx,httpContent,globalId,true);

        }
        else if(uri.endsWith("/file/megaupload")){
            MeqaUpload.MeqaUploadBuilder builder = MeqaUpload.builder();
            MeqaUpload record = null;
            if (decoder != null) {
                try {
                    decoder.offer(httpContent);
                    while (decoder.hasNext()) {
                        InterfaceHttpData data = decoder.next();
                        if (data.getHttpDataType() == HttpDataType.Attribute) {
                            Attribute attribute = (Attribute) data;
                            String name = attribute.getName();
                            String value = attribute.getValue();
                            logger.info("name:" + name + ",value:" + value);
                            switch (name) {
                                case "filename":
                                    builder.fileName(value);
                                    break;
                                case "mimetype":
                                    builder.mimeType(value);
                                    break;
                                case "contentlength":
                                    builder.contentLength(value);
                                    break;
                                case "shardcount":
                                    builder.shardSize(Integer.parseInt(value));
                                    break;
                                case "partfilesize":
                                    builder.partFileSize(Integer.parseInt(value));
                                    break;
                                default:
                                    logger.info("unknownParam:"+name);
                                    break;
                            }
                        }
                    }
                    record = builder.build().createGlobalId();
                    fileRecordManager.insertMeqaUpload(record);
                }
                catch (Exception e) {
                    responseContent.append(e.getMessage());
                    writeResponse(ctx.channel());
                    ctx.channel().close();
                    e.printStackTrace();
                    return;
                }
                if (httpContent instanceof LastHttpContent) {
                    responseContent.append(record.toString());
                    writeResponse(ctx.channel());
                    reset();
                }
            } else {
                writeResponse(ctx.channel());
            }
        }
        else {
            logger.error("Unknown Path: " + uri);
            responseContent.append("Unknown Path: ").append(uri);
            writeResponse(ctx.channel());
        }
    }

    private void reset() {
        request = null;
        decoder.destroy();
        decoder = null;
    }

    /**
     * Example of reading request by chunk and getting values from chunk to chunk
     */
    private String readHttpDataChunkByChunk(String globalId,boolean isChunked) throws EndOfDataDecoderException {
        String resultInfo = "";
        while (decoder.hasNext()) {
            InterfaceHttpData data = decoder.next();
            try {
                // new value
                resultInfo = writeHttpData(data,globalId,isChunked);
            } catch (IOException e) {
                e.printStackTrace();
                resultInfo =e.getMessage();
            } finally {
                data.release();
            }
        }
        return  resultInfo;
    }

    private String writeHttpData(InterfaceHttpData data,String globalId,boolean isChunked) throws IOException {

        logger.info("HTTP DATA name - " + data.getHttpDataType().name());

        String fileUploadResultInfo = "";

        if (data.getHttpDataType() == HttpDataType.Attribute) {
            Attribute attribute = (Attribute) data;
            String name = attribute.getName();
            String value = attribute.getValue();
            logger.info("name - " + name + ", value - " + value);
            fileUploadResultInfo =  "\"name - \" + name + \", value - \" + value";

        } else if (data.getHttpDataType() == HttpDataType.FileUpload) {
            FileUpload fileUpload = (FileUpload) data;
            if (fileUpload.isCompleted()) {

                // print file info
                logger.info("data - " + data);
                logger.info("File name: " + fileUpload.getFilename()+", length - "+fileUpload.length());
                logger.info("File isInMemory - " + fileUpload.isInMemory());

                //isChuned 是否分块上传
                if(isChunked) {
                    logger.info("File meqaUpload to localDisk as tempFile...");

                    MeqaUpload record = fileRecordManager.findMeqaInfoById(globalId);

                    if(record.getCurrentShard()<record.getShardSize()) {
                        boolean fileWriteStatus = false;
                        try (
                                FileInputStream uploadFileStream = new FileInputStream(fileUpload.getFile());
                                FileOutputStream diskTempFileStream = new FileOutputStream(new File("/"+fileUpload.getFilename()));
                                FileChannel uploadFileChannel = uploadFileStream.getChannel();
                                FileChannel diskTempFileChannel = diskTempFileStream.getChannel()
                        ){
                            uploadFileChannel.transferTo(0, uploadFileChannel.size(), diskTempFileChannel);
                            fileWriteStatus = true;
                        } catch (IOException e) {
                            logger.error(e);
                        }
                        finally {
                            decoder.removeHttpDataFromClean(fileUpload);
                        }

                        if (fileWriteStatus) {
                            int updateCount = fileRecordManager.incrementCurrentSize(globalId);

                            if (record.getCurrentShard()+1 < record.getShardSize())
                                fileUploadResultInfo = "203";

                            else if (record.getCurrentShard() +1 == record.getShardSize()) {

                                FileUtil fileUtil = new FileUtil();

                                String fileName = record.getFileName().split("\\.")[0];
                                // 合并成新文件
                                byte[] fileByte = fileUtil.mergePartFiles(fileName, record.getPartFileSize(),record.getFileName());

                                fileUploadResultInfo = "200  meqa Files combine completed!";

                                Response<FileRecord> result = getFileRecordResponse(fileByte,record.getFileName(),globalId);

                                fileUploadResultInfo += JsonFormat.convertJson(result.getData()).get();
                            }
                        }
                    }
                    else
                        fileUploadResultInfo = "500  Duplicate Files";
                }
                else{
                    Response<FileRecord> result = getFileRecordResponse(fileUpload.get(),fileUpload.getFilename(),globalId);

                    decoder.removeHttpDataFromClean(fileUpload);

                    fileUploadResultInfo = JsonFormat.convertJson(result.getData()).get();
                }
            }else {
                logger.info("File to be continued!");
                fileUploadResultInfo =  "File to be continued!";
            }
        }
        return fileUploadResultInfo;
    }

    private Response<FileRecord> getFileRecordResponse(byte[] file, String fileName,String globalId) throws IOException {
        logger.info("File upload to dfs...");

        Response<FileRecord> result = fileService.upload(file,fileName, globalId);

        logger.info("File upload to dfs completed...");

        logger.info("File decoder remove.");

        return result;
    }

    private void writeResponse(Channel channel) {
        logger.info("writeResponse ...");
        // Convert the response content to a ChannelBuffer.
        ByteBuf buf = copiedBuffer(responseContent.toString(), CharsetUtil.UTF_8);
        responseContent.setLength(0);

        // Decide whether to close the connection or not.
        boolean close = HttpHeaders.Values.CLOSE.equalsIgnoreCase(request.headers().get(CONNECTION))
                || request.getProtocolVersion().equals(HttpVersion.HTTP_1_0)
                && !HttpHeaders.Values.KEEP_ALIVE.equalsIgnoreCase(request.headers().get(CONNECTION));

        // Build the response object.
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, buf);

        response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");

        if (!close) {
            // There's no need to add 'Content-Length' header
            // if this is the last response.
            response.headers().set(CONTENT_LENGTH, buf.readableBytes());
        }

        Set<Cookie> cookies;
        String value = request.headers().get(COOKIE);
        if (value == null) {
            cookies = Collections.emptySet();
        } else {
//            cookies = CookieDecoder.decode(value);
            cookies = ServerCookieDecoder.STRICT.decode(value);
        }
        if (!cookies.isEmpty()) {
            // Reset the cookies if necessary.
            for (Cookie cookie : cookies) {
//                response.headers().add(SET_COOKIE, ServerCookieEncoder.encode(cookie));
                response.headers().add(HttpHeaders.Names.SET_COOKIE, ServerCookieEncoder.STRICT.encode(cookie));
            }
        }
        // Write the response.
        ChannelFuture future = channel.writeAndFlush(response);
        // Close the connection after the write operation is done if necessary.
        if (close) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.info(responseContent.toString(), cause);
        ctx.channel().close();
    }


    private static final Pattern INSECURE_URI = Pattern.compile(".*[<>&\"].*");
    private static final Pattern URI_REPLACER = Pattern.compile("\\?.*");

    public static final String HTTP_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss zzz";
    public static final String HTTP_DATE_GMT_TIMEZONE = "GMT";
    public static final int HTTP_CACHE_SECONDS = 60;

    private static final byte[] CONTENT = { 'P','a','n','g' };


    private static String sanitizeUri(String uri) {
        // Decode the path.
        try {
            uri = URLDecoder.decode(uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new Error(e);
        }

        if (uri.isEmpty() || uri.charAt(0) != '/') {
            return null;
        }

        uri = URI_REPLACER.matcher(uri).replaceAll("");
        if(uri.charAt(uri.length()-1) =='/')
            uri = uri.substring(0,uri.length()-1);
        // Convert file separators.
        uri = uri.replace('/', File.separatorChar);

        // Simplistic dumb security check.
        // You will have to do something serious in the production environment.
        if (uri.contains(File.separator + '.') ||
                uri.contains('.' + File.separator) ||
                uri.charAt(0) == '.' || uri.charAt(uri.length() - 1) == '.' ||
                INSECURE_URI.matcher(uri).matches()) {
            return null;
        }

        return uri;
    }

    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, status, Unpooled.copiedBuffer("Failure: " + status + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");

        // Close the connection as soon as the error message is sent.
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private static void sendNotModified(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, NOT_MODIFIED);
        setDateHeader(response);

        // Close the connection as soon as the error message is sent.
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private static void setDateHeader(FullHttpResponse response) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(HTTP_DATE_FORMAT, Locale.US);
        dateFormatter.setTimeZone(TimeZone.getTimeZone(HTTP_DATE_GMT_TIMEZONE));

        Calendar time = new GregorianCalendar();
        response.headers().set(DATE, dateFormatter.format(time.getTime()));
    }

    private static void setContentTypeHeader(HttpResponse response, FileRecord file) {
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        response.headers().set(CONTENT_TYPE, mimeTypesMap.getContentType(file.getFullName()) + "; charset=" + file.getHttpEncoding());
    }

    private static void setDateAndCacheHeaders(HttpResponse response, FileRecord fileToCache) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(HTTP_DATE_FORMAT, Locale.US);
        dateFormatter.setTimeZone(TimeZone.getTimeZone(HTTP_DATE_GMT_TIMEZONE));

        // Date header
        Calendar time = new GregorianCalendar();
        response.headers().set(DATE, dateFormatter.format(time.getTime()));

        // Add cache headers
        time.add(Calendar.SECOND, HTTP_CACHE_SECONDS);
        response.headers().set(EXPIRES, dateFormatter.format(time.getTime()));
        response.headers().set(CACHE_CONTROL, "private, max-age=" + HTTP_CACHE_SECONDS);
        response.headers().set(
                LAST_MODIFIED, dateFormatter.format(fileToCache.getUpdateTime()));
    }



}
