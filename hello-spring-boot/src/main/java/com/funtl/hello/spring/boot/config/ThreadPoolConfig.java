package com.funtl.hello.spring.boot.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置
 */
//@EnableAsync
@Configuration
public class ThreadPoolConfig implements AsyncConfigurer {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        // cpu 核心数 *2 ,线程大了会加大 cpu 开销 降低性能
        taskExecutor.setMaxPoolSize(20);
        //线程池所使用的缓冲队列
        taskExecutor.setQueueCapacity(200);
        //线程池维护线程所允许的空闲时间
        taskExecutor.setKeepAliveSeconds(2000);
        taskExecutor.setThreadNamePrefix("xxx-thread-");
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
