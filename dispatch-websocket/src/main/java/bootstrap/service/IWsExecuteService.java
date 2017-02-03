package bootstrap.service;

import com.nextcont.ecm.fileengine.bean.request.TransRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/8
 * Time: 11:20
 * To change this template use File | Settings | File Templates.
 */
public interface IWsExecuteService {

    String transtion(TransRequest request);
}
