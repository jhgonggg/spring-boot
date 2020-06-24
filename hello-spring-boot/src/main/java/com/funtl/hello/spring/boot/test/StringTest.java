package com.funtl.hello.spring.boot.test;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author qy
 * @date 2020/6/24 10:09
 * @description
 */
public class StringTest {

    public static void main(String[] args) {
        String s = "abcd";

        // 删除最后一个字符
        // $ 匹配输入行尾
        // ^ 匹配输入字行首
        // . 匹配除“\n”和"\r"之外的任何单个字符。
        String result1 = Optional.ofNullable(s).map(str -> str.replaceAll(".$", "")).orElse(s);
        System.out.println(result1);
        // 删除第一个字符
        String result2 = Optional.ofNullable(s).map(str -> str.replaceAll("^.", "")).orElse(s);
        System.out.println(result2);

        List<String> list = Arrays.asList("1", "2", "3");
        String join = StringUtils.join(list, ",");           // 1,2,3
        System.out.println(join);

        String join1 = String.join(",", list);

        System.out.println(join1);
    }
}
