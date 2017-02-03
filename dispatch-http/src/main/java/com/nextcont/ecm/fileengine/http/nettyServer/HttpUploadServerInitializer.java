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

import com.nextcont.ecm.fileengine.file.FileService;
import com.nextcont.ecm.fileengine.persistence.manager.FileRecordManager;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpUploadServerInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;

    private final FileService fileService;

    private final FileRecordManager fileRecordManager;


    public HttpUploadServerInitializer(SslContext sslCtx, FileService fileService,FileRecordManager fileRecordManager) {
        this.sslCtx = sslCtx;
        this.fileService = fileService;
        this.fileRecordManager = fileRecordManager;
    }


    @Override
    public void initChannel(SocketChannel ch) {
        System.out.println("--------------- initChannel ----------------");
        ChannelPipeline pipeline = ch.pipeline();

        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }
//        pipeline.addLast(new HttpRequestDecoder());
//        pipeline.addLast(new HttpResponseEncoder());

        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());

        pipeline.addLast(new HttpUploadServerHandler(fileService,fileRecordManager));



    }
}
