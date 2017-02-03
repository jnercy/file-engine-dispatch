package bootstrap.socket;


/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/9
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
public enum SocketActionType {
    transition,callback,unknown;

    private String type;


    SocketActionType (String type){
        this.type = type;
    }

    SocketActionType() {
    }

    public static SocketActionType getSocketActionType(String type){
        for(SocketActionType u : SocketActionType.values()){
            if(u.name().equals(type))
                return u;
        }
        return  SocketActionType.unknown;

    }

    public String getType() {
        return type;
    }

}
