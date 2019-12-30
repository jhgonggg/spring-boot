package com.funtl.hello.spring.boot.role;

import com.funtl.hello.spring.boot.service.Operate;

/**
 * @author qy
 * @date 2019/12/20 17:23
 * @description 策略模式
 */
public class RootAdminRole implements Operate {
    @Override
    public String op(String role) {
        return role + " has AAA permission" ;
    }
}
