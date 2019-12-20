package com.funtl.hello.spring.boot.enums;

import com.funtl.hello.spring.boot.service.Operate;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author qy
 * @date 2019/12/20 17:00
 * @description if/else 转 枚举 ，角色枚举
 */
@AllArgsConstructor
@Getter
public enum RoleEnum implements Operate {

    // 系统管理员(有A操作权限)
    ROLE_ROOT_ADMIN {
        @Override
        public String op(String role) {
            return role + " has AAA permission";
        }
    },

    ROLE_ORDER_ADMIN{
        @Override
        public String op(String role) {
            return role + " has BBB permission";
        }
    }
    ;

}
