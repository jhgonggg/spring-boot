package com.funtl.hello.spring.boot.response;

/**
 * @author qy
 * @date 2019/12/18 18:06
 * @description
 */
public final class Response {

    private Object data;
    private Integer code;
    private String msg;
    private boolean success;

    public <T> T getData() {
        return (T) data;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public Response setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Response setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public boolean isSuccess() {
        return MsgCode.SUCCESS.getCode().equals(this.code) && success;
    }

    public Response setSuccess(boolean success) {
        this.success = success;
        return this;
    }

}
