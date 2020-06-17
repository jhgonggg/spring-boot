package com.funtl.hello.spring.boot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PmUriEnum {

    STARTWORKFLOW("/workFlow/startWorkFlow", "新增工作");

    /**
     * 请求的资源接口
     */
    private String uri;

    /**
     * 描述
     */
    private String desc;
}
