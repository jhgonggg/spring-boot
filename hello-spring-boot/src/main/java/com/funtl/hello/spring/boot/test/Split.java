package com.funtl.hello.spring.boot.test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author qy
 * @date 2020/7/10 11:01
 * @description
 * trimResults()             将结果中的空格删除
 * trimResults(CharMatcher) 移除匹配字符
 * omitEmptyStrings()      移去结果中的空字符串
 * limit(int)              达到指定数目后停止字符串的划分
 */
public class Split {

    public static void main(String[] args) {

        Iterable<String> split = Splitter.on(',').trimResults(CharMatcher.is('_')).split("_a ,_b_ ,c__");
        // [a , b_ , c]

        List<String> list = Lists.newArrayList(split);

        System.out.println(split);

        Iterable<String> split3 = Splitter.on(',').trimResults(CharMatcher.is('_')).split("_a,_b_,c__");
        // [a, b, c]

        System.out.println(split3);

        Iterable<String> split1 = Splitter.on(',').trimResults().split("a, b, c, d");
        // [a, b, c, d]

        System.out.println(split1);


        Iterable<String> split2 = Splitter.on(',').split("a, b, c, d");
        // [a,  b,  c,  d]   有空格

        System.out.println(split2);

        Iterable<String> split4 = Splitter.on(',').omitEmptyStrings().split("a,,c,d");
       // [a, c, d]
        System.out.println(split4);

        Iterable<String> split5 = Splitter.on(",").split(",a,,b,");

        System.out.println(split5);
        // [, a, , b, ]  会保存尾部的分隔符

        String[] split6 = ",a,,b,".split(",");
        System.out.println(Arrays.toString(split6));
        // [, a, , b]  不会保留尾部的分隔符


        Map<String, String> split7 = Splitter.on("#").withKeyValueSeparator(":").split("1:2#3:4");

        System.out.println(split7);

    }
}
