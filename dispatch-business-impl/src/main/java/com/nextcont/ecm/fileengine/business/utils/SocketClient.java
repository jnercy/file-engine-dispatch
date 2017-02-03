package com.nextcont.ecm.fileengine.business.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;

@ClientEndpoint
public class SocketClient {

    private static Logger logger = LoggerFactory.getLogger(SocketClient.class);


    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to endpoint:"+ session.getBasicRemote());
    }

    @OnMessage
    public void onMessage(String message) {
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }
}