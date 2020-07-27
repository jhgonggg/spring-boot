package com.funtl.hello.spring.boot.config;

import com.funtl.hello.spring.boot.response.MsgCode;

/**
 * @author qy
 * @date 2020/7/27 11:38
 * @description
 */
public class SouthcnException extends RuntimeException {

    private static final long serialVersionUID = 3022481503667660306L;
    private Integer code;
    private String msg;
    private Object result;

    public SouthcnException(MsgCode msgCode) {
        this.code = msgCode.getCode();
        this.msg = msgCode.getMsg();
    }

    public SouthcnException(MsgCode msgCode, String msg) {
        this.code = msgCode.getCode();
        this.msg = msg;
    }

    public SouthcnException(MsgCode msgCode, String msg, Object result) {
        this.code = msgCode.getCode();
        this.msg = msg;
        this.result = result;
    }

    public SouthcnException(MsgCode msgCode, Object result) {
        this.code = msgCode.getCode();
        this.msg = msgCode.getMsg();
        this.result = result;
    }

    /** @deprecated */
    @Deprecated
    public SouthcnException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /** @deprecated */
    @Deprecated
    public SouthcnException(Integer code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public String getMsg() {
        return this.msg;
    }

    public Object getResult() {
        return this.result;
    }

    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
