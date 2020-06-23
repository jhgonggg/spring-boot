package com.funtl.hello.spring.boot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.Assert;

/**
 * Description: 工作流类型
 *
 */
@Getter
@AllArgsConstructor
public enum WorkFlowTypeEnum {

    /**
     * 全部
     */
    ALL(0,"全部"),
    SHARE(1, "分享"),
    NOTE(2, "日志"),
    VISIT(3, "采访"),
    APPROVAL(4, "审批"),
    CLUE(5, "报料"),
    CHECKIN(6, "签到"),
    REPORT(7, "报题"),
    MANUSCRIPTS(8, "交稿"),
    TASK(9, "任务"),
    REVIEWER(10, "编审"),
    ELEVEN(11,""),
    PROOFREAD(12,"校对"),

    UNKNOWN(9999, "未知");

    private Integer type;
    private String name;

    public static WorkFlowTypeEnum getInstance(Integer type) {
        Assert.notNull(type, "type不能为空!");
        for (WorkFlowTypeEnum typeEnum : values()) {
            if (type.equals(typeEnum.getType())) {
                return typeEnum;
            }
        }
        return UNKNOWN;
    }
}