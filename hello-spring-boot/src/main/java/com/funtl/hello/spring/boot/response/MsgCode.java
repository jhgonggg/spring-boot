package com.funtl.hello.spring.boot.response;

import java.util.stream.Stream;

/**
 * @author qy
 * @date 2019/12/18 18:07
 * @description
 */
public enum MsgCode {

    NO_LOGIN(0, "您还未登录，请先登录"),

    SUCCESS(200, "成功"),
    FAIL(100, "网络竟然崩溃了"),

    SYSTEM_ERROR(900, "系统繁忙");


    private Integer code;
    private String msg;


    private MsgCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    /**
     * 根据code值查找
     *
     * @param code
     * @return
     */
    public static MsgCode codeOfMsgCode(Integer code) {
        return Stream.of(MsgCode.values()).filter((value) -> value.getCode().equals(code)).findFirst().orElse(null);
    }

}
