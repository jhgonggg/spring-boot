package com.funtl.hello.spring.boot.factory;

import com.funtl.hello.spring.boot.role.OrderAdminRole;
import com.funtl.hello.spring.boot.role.RootAdminRole;
import com.funtl.hello.spring.boot.service.Operate;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author qy
 * @date 2019/12/20 17:25
 * @description  if/else 转换工厂模式
 */
public class RoleFactory {

    public static Map<String, Operate> roleOperationMap = Maps.newHashMap();

    // 在静态块中先把初始化工作全部做完
    static {
        roleOperationMap.put("ROLE_ROOT_ADMIN", new RootAdminRole());
        roleOperationMap.put("ROLE_ORDER_ADMIN", new OrderAdminRole());
    }

    public static Operate getOp(String roleName) {
        return roleOperationMap.get(roleName);
    }


    public static void main(String[] args) {
        String op = RoleFactory.getOp("ROLE_ROOT_ADMIN").op("ROLE_ROOT_ADMIN");
        System.out.println(op);
    }
}
