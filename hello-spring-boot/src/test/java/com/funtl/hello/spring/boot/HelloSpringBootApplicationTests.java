package com.funtl.hello.spring.boot;

import com.funtl.hello.spring.boot.entity.YbUser;
import com.funtl.hello.spring.boot.mapper.YbUserMapper;
import com.funtl.hello.spring.boot.redis.RedisManager;
import com.funtl.hello.spring.boot.util.SpringContextHolder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
/* classes = HelloSpringBootApplication.class 为了加载 application.yml 的配置*/
@SpringBootTest(classes = Application.class)
public class HelloSpringBootApplicationTests {
    @Autowired
    private YbUserMapper ybUserMapper;

    @Test
    public void contextLoads() {
        System.out.println("hello-springboot-test");
    }

    @Test
    public void page() {
        PageHelper.orderBy("username asc");
        PageHelper.startPage(1, 100);
        PageInfo<YbUser> pageInfo = new PageInfo<>(ybUserMapper.selectAll());
        List<YbUser> list = pageInfo.getList();
        list.forEach(user -> System.out.println(user.getUsername()));

        Object anInt = SpringContextHolder.getBean(Environment.class);
        System.out.println(anInt);
    }


    @Test
    public void reids() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(new test2()).start();
        }
    }

    private static CountDownLatch cdl = new CountDownLatch(5);

    public class test2 implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName());
                cdl.countDown();
                cdl.await();
                System.out.println(System.currentTimeMillis());
                // TODO Could not get a resource from the pool 、Caused by: java.lang.IllegalStateException: Pool not open
                RedisManager.set(String.valueOf(Thread.currentThread().getId()), Thread.currentThread().getName());
                System.out.println(1111111111);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

