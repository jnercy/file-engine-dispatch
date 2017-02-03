package com.nextcont.ecm.fileengine.bean.response;


import com.nextcont.ecm.fileengine.bean.BusinessCode;

/**
 * 代表响应
 * Created by wangxudong on 16/1/5.
 */
public class Response<T> implements java.io.Serializable{

    private static final long serialVersionUID = 2L;
    
    private Boolean success;
    private T data;
    private BusinessCode code;
    private String message;
    private Throwable reason;

    public Response(Boolean success, T data, BusinessCode code, String message, Throwable t) {
        this.success = success;
        this.data = data;
        this.code = code;
        this.message = message;
        this.reason = t;
    }
    
    public static <R> Response<R> success(R data){
        return new Response<R>(true,data,BusinessCode.SUCCESS,null,null);
    }

    public static <R> Response<R> success(R data,String message){
        return new Response<R>(true,data,BusinessCode.SUCCESS,message,null);
    }

    public static <R> Response<R> failure(R data,BusinessCode code,String message,Throwable t){
        return new Response<R>(false,data,code,message,t);
    }

    public static <R> Response<R> failure(R data,BusinessCode code,String message){
        return new Response<R>(false,data,code,message,null);
    }

    public static <R> Response<R> failure(BusinessCode code,String message){
        return new Response<R>(false,null,code,message,null);
    }

    public static <R> Response<R> failure(BusinessCode code,Throwable t){
        return new Response<R>(false,null,code,t.getMessage(),t);
    }

    public static <R> Response<R> failure(R data,BusinessCode code,Throwable t){
        return new Response<R>(false,data,code,t.getMessage(),t);
    }

    public Boolean getSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public BusinessCode getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getReason() {
        return reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response<?> response = (Response<?>) o;

        if (success != null ? !success.equals(response.success) : response.success != null) return false;
        if (data != null ? !data.equals(response.data) : response.data != null) return false;
        if (code != response.code) return false;
        if (message != null ? !message.equals(response.message) : response.message != null) return false;
        return !(reason != null ? !reason.equals(response.reason) : response.reason != null);

    }

    @Override
    public int hashCode() {
        int result = success != null ? success.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", reason=" + reason +
                '}';
    }
}
