package com.funtl.hello.spring.boot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {

    /**
     * 错误枚举
     */
    ARGS_ERROR("参数异常"),

    ;

    private String msg;
}
