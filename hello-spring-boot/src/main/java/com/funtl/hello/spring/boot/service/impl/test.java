package com.funtl.hello.spring.boot.service.impl;

import cn.hutool.core.util.StrUtil;

/**
 * @author qy
 * @date 2019/9/29 15:33
 * @description
 */
public class test {

    public static void main(String[] args) {
       String s="1,2,3,";
        String s1 = StrUtil.removeSuffix(s,",");
        System.out.println(s1);


    }
}
