package com.funtl.hello.spring.boot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.Assert;

/**
 * description 按钮枚举类
 */
@Getter
@AllArgsConstructor
public enum ButtonEnum {
    //日志 点评
    NOTE_COMMENT("v_1","点评"),

    //审批
    APPROVAL_PASS("v_2","通过"),
    APPROVAL_TRANSFER("v_3","转审批"),
    APPROVAL_REJECT("v_4","驳回"),
    APPROVAL_APPEAL("v_5","申诉"),
    APPROVAL_CANCELREJECT("v_6","撤销驳回"),
    APPROVAL_KEEPREJECT("v_7","维持驳回"),

    //编审
    MANUSCRIPT_SUBMITREAUDIT("v_58","提交重审"),
    MANUSCRIPT_AUDITAFTERMODIFY("v_59","修改后重审"),

    MANUSCRIPT_AGREESEND("v_30","同意交稿"),

    UNKNOWN("v_99999", "未知");


    private String id;
    private String name;

    public static ButtonEnum getInstance(String id) {
        Assert.notNull(id, "id不能为空!");
        for (ButtonEnum typeEnum : ButtonEnum.values()) {
            if (id.equals(typeEnum.getId())) {
                return typeEnum;
            }
        }
        return UNKNOWN;
    }
}
