package com.funtl.hello.spring.boot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author qy
 * @date 2019/9/27 12:38
 * @description 通讯员类型枚举
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CorrespondentTypeEnum {

    PRESS_SECRETARY(1,"新闻秘书"),
    CORRESPONDENT(2,"通讯员"),
    XLS(999,"xls");

    private Integer type;
    private String desc;
}
