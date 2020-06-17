package com.funtl.hello.spring.boot;

import com.funtl.hello.spring.boot.entity.YbUser;
import com.funtl.hello.spring.boot.mapper.YbUserMapper;
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
    public void page(){
        PageHelper.orderBy("username asc");
        PageHelper.startPage(1,100);
        PageInfo<YbUser> pageInfo=new PageInfo<>(ybUserMapper.selectAll());
        List<YbUser> list = pageInfo.getList();
        list.forEach(user -> System.out.println(user.getUsername()));

        Object anInt = SpringContextHolder.getBean(Environment.class);
        System.out.println(anInt);
    }
}

