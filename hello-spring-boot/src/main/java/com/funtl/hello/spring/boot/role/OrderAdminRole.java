package com.funtl.hello.spring.boot.role;

import com.funtl.hello.spring.boot.service.Operate;

/**
 * @author qy
 * @date 2019/12/20 17:24
 * @description
 */
public class OrderAdminRole implements Operate {
    @Override
    public String op(String role) {
        return role + " has BBB permission";
    }
}
