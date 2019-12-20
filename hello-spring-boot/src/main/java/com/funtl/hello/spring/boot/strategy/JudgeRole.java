package com.funtl.hello.spring.boot.strategy;

import com.funtl.hello.spring.boot.role.RootAdminRole;
import com.funtl.hello.spring.boot.service.Operate;

/**
 * @author qy
 * @date 2019/12/20 17:40
 * @description
 */
public class JudgeRole {
    public static String judge(Operate roleOperation,String role) {
        RoleContext roleContext = new RoleContext(roleOperation);
        return roleContext.execute(role);
    }

    public static void main(String[] args) {
        String result = JudgeRole.judge(new RootAdminRole(), "ni");

        System.out.println(result);
    }
}
