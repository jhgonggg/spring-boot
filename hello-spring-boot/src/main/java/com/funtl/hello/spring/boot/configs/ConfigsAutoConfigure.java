package com.funtl.hello.spring.boot.configs;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties({Config.class, RedisConfig.class})
public class ConfigsAutoConfigure {
}
