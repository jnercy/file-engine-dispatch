package com.nextcont.ecm.fileengine.common;

/**
 * 抽像的类，加入了生命周期控制
 * Created by wangxudong on 14-8-15.
 */
public abstract class AbstractLifeCycleComponent implements LifeCycleComponent{

    protected final LifeCycle lifeCycle = new LifeCycle();

    @Override
    public void start() throws Exception {
        if (!lifeCycle.canMoveToStarted()) {
            return ;
        }

        doStart();
        lifeCycle.moveToStarted();

    }

    protected abstract void  doStart() throws Exception;

    @Override
    public void stop() throws Exception {
        if (!lifeCycle.canMoveToStopped()) {
            return ;
        }

        lifeCycle.moveToStopped();
        doStop();
    }

    protected abstract void  doStop() throws Exception;
}
