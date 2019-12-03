package com.funtl.hello.spring.boot;

import com.funtl.hello.spring.boot.util.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import tk.mybatis.spring.annotation.MapperScan;

//  @SpringBootApplication 自动配置了注解扫描 扫描的包为 HelloSpringBootApplication 的包下(com.funtl.hello.spring.boot)
@SpringBootApplication
//  告诉 Mybatis 数据访问接口的位置,让 Mybatis 与关系对象映射文件做匹配 用来操作mapper的业务 导入的包必须是 import tk.mybatis.spring.annotation.MapperScan 否则报错
@MapperScan(basePackages = "com.funtl.hello.spring.boot.mapper")
public class HelloSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringBootApplication.class, args);

        Object bean = SpringContextHolder.getBean(Environment.class);
        System.out.println(bean);
    }

}

