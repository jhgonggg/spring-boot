package com.funtl.hello.spring.boot.test;

import org.springframework.stereotype.Component;

/**
 * @author qy
 * @date 2019/12/23 16:53
 * @description
 */
@Component
public class Test {

//    @Scheduled(cron = "0/3 * * * * ?")
    public void test(){
        System.out.println("111111111111");
    }

}
