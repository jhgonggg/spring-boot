package com.funtl.hello.spring.boot.util;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Objects;

/**
 * @author qy
 * @date 2020/4/14 15:36
 * @description
 */
public class NumberUtil {
    /**
     *  数字正则表达式
     */
    private static final String NUMBER_REGEX = "-[0-9]+(.[0-9]+)?|[0-9]+(.[0-9]+)?";

    /**
     * 判断是否是数字，并四舍五入转换成 long 类型
     *
     * @param value 字符串
     * @return 数字
     */
    private Long castNumber(String value) {
        if (Objects.nonNull(value) && value.matches(NUMBER_REGEX)) {
            value = String.format("%.0f", Double.parseDouble(value));
            return Long.parseLong(value);
        }
        return NumberUtils.LONG_ZERO;
    }

}
