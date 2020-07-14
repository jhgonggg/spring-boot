package com.funtl.hello.spring.boot.configs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;


@Getter
@Setter
//@Component 2种写法--- 不写 Components 注解 则需要 spring.factories 文件 和 ConfigsAutoConfigure 类 注册
@ConfigurationProperties(prefix = "spring.redis")
@ToString
public class RedisConfig {

    private String host;
    private Integer port;
    private String password;
    private Integer database;

    /**
     * pool最大Jedis实例
     */
    private Integer maxTotal;

    private Integer maxIdle;

    private Integer minIdle;

    private Map<String, String> test;

}
