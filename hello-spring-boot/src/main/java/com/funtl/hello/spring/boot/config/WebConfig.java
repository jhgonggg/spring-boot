package com.funtl.hello.spring.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author qy
 * @date 2019/12/3 15:08
 * @description  把 Interceptor 注册到 springboot 中
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private FangshuaInterceptor fangshuaInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(fangshuaInterceptor);
    }
}

