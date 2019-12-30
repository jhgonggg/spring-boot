package com.funtl.hello.spring.boot.service.strategyTest;

import com.funtl.hello.spring.boot.enums.RoleEnum;

/**
 * @author qy
 * @date 2019/12/20 17:05
 * @description 测试 if/else 转换成枚举
 */
public class RoleTest {

    public static String judge(String roleName) {
        return RoleEnum.valueOf(roleName).op(roleName);
    }

    public static void main(String[] args) {
        String result = RoleTest.judge("ROLE_ROOT_ADMIN");
        System.out.println(result);

        String result2 = RoleTest.judge("ROLE_ORDER_ADMIN");
        System.out.println(result2);

    }
}
