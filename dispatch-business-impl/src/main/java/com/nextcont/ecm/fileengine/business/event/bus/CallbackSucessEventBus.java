package com.nextcont.ecm.fileengine.business.event.bus;

import com.google.common.eventbus.EventBus;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/30
 * Time: 18:24
 * To change this template use File | Settings | File Templates.
 */
public class CallbackSucessEventBus {

    private final static EventBus eventBus = new EventBus();


    public static void post(Object event) {
        eventBus.post(event);
    }


    public static void register(Object handler) {
        eventBus.register(handler);
    }


    public static void unregister(Object handler) {
        eventBus.unregister(handler);
    }

}
