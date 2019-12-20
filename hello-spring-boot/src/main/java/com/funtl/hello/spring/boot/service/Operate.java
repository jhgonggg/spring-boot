package com.funtl.hello.spring.boot.service;

/**
 * @author qy
 * @date 2019/12/20 16:58
 * @description
 */
public interface Operate {
    // 表示某个角色可以做哪些op操作
    String op(String role);
}
