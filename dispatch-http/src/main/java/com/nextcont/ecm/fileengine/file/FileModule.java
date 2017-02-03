package com.nextcont.ecm.fileengine.file;

import com.nextcont.ecm.fileengine.file.fastdfs.FastdfsFileServiceImpl;
import com.google.inject.AbstractModule;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/20
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class FileModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(FileService.class).to(FastdfsFileServiceImpl.class).asEagerSingleton();
    }
}
