package com.nextcont.ecm.fileengine.file;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/3
 * Time: 10:55
 * To change this template use File | Settings | File Templates.
 */
@Setter
@Getter
public class Thumbnail {

    private String imageUrl;

    private String mimeType;

    public Thumbnail(String imageUrl, String mimeType) {
        this.imageUrl = imageUrl;
        this.mimeType = mimeType;
    }
}
