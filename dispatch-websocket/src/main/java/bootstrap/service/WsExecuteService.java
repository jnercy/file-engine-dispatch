package bootstrap.service;

import com.nextcont.ecm.fileengine.api.TransitionManager;
import com.nextcont.ecm.fileengine.bean.request.TransRequest;
import com.nextcont.ecm.fileengine.bean.response.TransResponse;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/8
 * Time: 10:39
 * To change this template use File | Settings | File Templates.
 */
@Service
public class WsExecuteService implements IWsExecuteService {

    @Resource
    private TransitionManager transitionManager;

    @PostConstruct
    public void init(){
        System.out.println(this.getClass().getName()+"init successed");
    }


    @Override
    public String transtion(TransRequest request){
        TransResponse response =  transitionManager.transition(request);
        return response.toString();
    }

}
