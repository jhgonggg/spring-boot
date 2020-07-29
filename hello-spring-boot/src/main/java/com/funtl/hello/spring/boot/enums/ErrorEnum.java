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
    FILE_FORMAT_NOT_CORRECT("文件格式不正确，仅支持 xls 格式"),
    PHONE_NOT_CORRECT("手机号格式不正确"),
    NAME_NULL("姓名不能为空"),
    TYPE_NULL("类型不能为空"),
    TYPE_ERROR("类型错误，必须为通讯员或者新闻秘书"),
    UINT_NULL("单位不能为空"),
    AREA_NULL("地区不能为空"),
    AVATAR_FAIL("头像导入失败"),
    ;
    private String msg;
}
