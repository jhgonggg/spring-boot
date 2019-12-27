package com.funtl.hello.spring.boot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author qy
 * @date 2019/12/27 17:03
 * @description 协议勾选枚举
 */
@AllArgsConstructor
@Getter
public enum AgreeEnum {
    YES("1" ,"勾选"),

    NO("0" ,"未勾选")

    ;

    private String type ;

    private String desc ;
}
