package com.funtl.hello.spring.boot.init;

import com.funtl.hello.spring.boot.test.ColumnCacheJob;
import com.funtl.hello.spring.boot.util.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * description : 实现启动加载类 ApplicationRunner/CommandLineRunner 用于容器初始化bean后运行
 */
@Slf4j
@Component
public class ApiRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        ReviewerPersonThread reviewerPersonThread = SpringContextHolder.getBean("reviewerPersonThread");
        reviewerPersonThread.setName("reviewer-look-thread");
        reviewerPersonThread.start();
        ColumnCacheJob.init();
    }
}
