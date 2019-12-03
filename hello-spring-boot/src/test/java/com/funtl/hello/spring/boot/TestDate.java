package com.funtl.hello.spring.boot;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author qy
 * @date 2019/11/21 16:19
 * @description
 */
public class TestDate {

    public static void main(String[] args) {

        Date date = new Date();
        System.out.println(date);
        DateTime time = DateUtil.offsetSecond(date, -1);

        System.out.println(time);

    }
}
