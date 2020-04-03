package com.funtl.hello.spring.boot.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
//@Component 2种写法--- 不写 Components 注解 则需要 spring.factories 文件 和 ConfigsAutoConfigure 类 注册
@ConfigurationProperties(prefix = "spring.thymeleaf")
public class Config {

    private String cache;
    private String mode;
    private String encoding;

}
