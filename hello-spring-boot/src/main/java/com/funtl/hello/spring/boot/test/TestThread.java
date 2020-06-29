package com.funtl.hello.spring.boot.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author qy
 * @date 2020/6/29 14:56
 * @description 线程
 */
@Slf4j
@Component
public class TestThread {

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    /**
     * 第一次任务开始延迟时间
     */
    private static final long INIT_DELAY_SECONDS = TimeUnit.MINUTES.toSeconds(1L);

    /**
     * 每次任务间隔时间
     */
    private static final long DELAY_INTERVAL_SECONDS = 10L;

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(
                1,
                new BasicThreadFactory.Builder()
                        .namingPattern("app-config-job-%d")
                        .daemon(true)  // 守护线程
                        .build()
        );
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(new AppConfigTimer(), INIT_DELAY_SECONDS, DELAY_INTERVAL_SECONDS, TimeUnit.SECONDS);
        log.info("启动页:更新任务启动");
    }

    @PreDestroy
    public void destroy() {
        scheduledThreadPoolExecutor.shutdown();
        System.out.println("=====");
    }

    static class AppConfigTimer extends Thread {

        @Override
        public void run() {
            try {
                System.out.println(1111);
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (Exception e) {
                log.error("启动页:刷新缓存异常", e);
            }
        }

    }
}
