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

import com.google.inject.Inject;
import com.nextcont.ecm.fileengine.common.AbstractLifeCycleComponent;
import com.nextcont.ecm.fileengine.file.FileService;
import com.nextcont.ecm.fileengine.http.HttpServer;
import com.nextcont.ecm.fileengine.persistence.manager.FileRecordManager;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;


/**
 * A HTTP server showing how to use the HTTP multipart package for file uploads and decoding post data.
 */
public final class HttpUploadServer  extends AbstractLifeCycleComponent implements HttpServer {

    public boolean SSL = System.getProperty("ssl") != null;
    public int PORT = Integer.parseInt(System.getProperty("port", SSL? "8443" : "9200"));

    public FileService fileService;

    public FileRecordManager fileRecordManager;

    @Inject
    public HttpUploadServer(FileService fileService,FileRecordManager fileRecordManager){
        this.fileService = fileService;
        this.fileRecordManager = fileRecordManager;
    }

    private EventLoopGroup boss;
    private EventLoopGroup worker;



    @Override
    protected void doStart() throws Exception {
        final SslContext sslCtx;
        if (SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }

        boss = new NioEventLoopGroup();
        worker = new NioEventLoopGroup();
        try {

            ServerBootstrap server = new ServerBootstrap();
            server.group(boss, worker);
            server.channel(NioServerSocketChannel.class);
            server.handler(new LoggingHandler(LogLevel.INFO));
            server.childHandler(new HttpUploadServerInitializer(sslCtx,fileService,fileRecordManager));

            Channel ch = server.bind(PORT).sync().channel();

            System.err.println("Open your web browser and navigate to " +
                    (SSL? "https" : "http") + "://127.0.0.1:" + PORT + '/');

            ch.closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    @Override
    protected void doStop() throws Exception {
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }
}
