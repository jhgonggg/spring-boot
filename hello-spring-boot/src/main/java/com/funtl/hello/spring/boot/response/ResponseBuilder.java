package com.funtl.hello.spring.boot.response;

/**
 * @author qy
 * @date 2019/12/18 18:09
 * @description
 */
public class ResponseBuilder {

    public static Response buildSuccess(){
        return build(true, MsgCode.SUCCESS.getCode(),null, MsgCode.SUCCESS.getMsg());
    }
    public static Response buildSuccess(Object data){
        return build(true, MsgCode.SUCCESS.getCode(),data, MsgCode.SUCCESS.getMsg());
    }

    public static Response buildSuccess(String msg,Object data){
        return build(true, MsgCode.SUCCESS.getCode(),data,msg);
    }

    public static Response buildFail(){
        return build(false, MsgCode.FAIL.getCode(),null,MsgCode.FAIL.getMsg());
    }

    public static Response buildFail(Object data){
        return build(false, MsgCode.FAIL.getCode(),data,MsgCode.FAIL.getMsg());
    }

    public static Response buildFail(String msg){
        return build(false, MsgCode.FAIL.getCode(),null,msg);
    }
    public static Response buildFail(Integer code, String msg){
        return build(false, code,null,msg);
    }

    public static Response buildFail(Integer code, String msg,Object data){
        return build(false, code,data,msg);
    }


    public static Response build(boolean success, Integer code){
        return build(success,code,null,null);
    }

    public static Response build(boolean success, Integer code, Object data){
        return build(success,code,data,null);
    }

    public static Response build(boolean success, Integer code, Object data, String msg){
        return new Response().setSuccess(success).setCode(code).setData(data).setMsg(msg);
    }
}
