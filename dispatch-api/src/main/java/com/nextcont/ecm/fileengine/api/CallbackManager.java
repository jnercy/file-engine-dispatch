package com.nextcont.ecm.fileengine.api;


/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/10/10
 * Time: 11:16
 * To change this template use File | Settings | File Templates.
 */
public interface CallbackManager {

    String notifySuccess(String globalId);

    String notifyFailed(String globalId);

}
