package com.nextcont.ecm.fileengine.common;

/**
 * 带生命周期的组件接口
 * Created by wangxudong on 14-8-13.
 */
public interface LifeCycleComponent {

    public void start() throws Exception;

    public void stop() throws Exception;
}
