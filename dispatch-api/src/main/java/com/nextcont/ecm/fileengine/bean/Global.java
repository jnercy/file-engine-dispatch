package com.nextcont.ecm.fileengine.bean;

import com.nextcont.ecm.fileengine.util.StringUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/21
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
@Data
public abstract class Global implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public String globalId;

    public void createGlobalId(){
        this.globalId = UUID.randomUUID().toString();
    }

    public boolean checkGlobalId(){
        return StringUtils.isNotEmpty(globalId);
    }

}
