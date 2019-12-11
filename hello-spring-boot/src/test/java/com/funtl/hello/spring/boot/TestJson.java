package com.funtl.hello.spring.boot;

import com.funtl.hello.spring.boot.util.PhoneUtil;

/**
 * @author qy
 * @date 2019/12/9 17:57
 * @description
 */
public class TestJson {
    public static void main(String[] args) {
        final String COMMUNICATE_PEOPLE = "(%s)%d";
        String s = String.format(COMMUNICATE_PEOPLE, "小米", 12131);
        System.out.println(s);

        System.out.println(PhoneUtil.isMobileNumber("18345612323"));

        System.out.println("getLiveGood".matches("getLiveView|getLiveHot|getLiveGood"));
    }



}
