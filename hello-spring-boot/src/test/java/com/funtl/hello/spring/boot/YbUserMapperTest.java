package com.funtl.hello.spring.boot;

import com.funtl.hello.spring.boot.entity.YbUser;
import com.funtl.hello.spring.boot.mapper.YbUserMapper;
import com.funtl.hello.spring.boot.service.impl.LoginServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
/* classes = HelloSpringBootApplication.class 为了加载 application.yml 的配置*/
@SpringBootTest(classes = HelloSpringBootApplication.class)
public class YbUserMapperTest {
    @Autowired
    private YbUserMapper ybUserMapper;

    /**
     * 条件查询
     */
    @Test
    public void select(){
        Example example =new Example(YbUser.class);
        example.createCriteria().andEqualTo("id",6L);
        YbUser ybUser = ybUserMapper.selectOneByExample(example);
        System.out.println(ybUser);
    }

    /**
     * 条件查询
     */
    @Test
    public void select2(){
        YbUser ybUser=new YbUser();
        ybUser.setId(6L);
        YbUser one = ybUserMapper.selectOne(ybUser);
        System.out.println(one);
    }

    /**
     * 模糊查询 排序
     */
    @Test
    public void like(){
        Example example =new Example(YbUser.class);
        example.createCriteria().andEqualTo("username","8888888");
        List<YbUser> users = ybUserMapper.selectByExample(example);
        System.out.println(users.isEmpty()+"--------");
        List<YbUser> userList = Optional.ofNullable(users).orElseGet(ArrayList::new).stream().filter(u -> u.getUsername().length() > 6).collect(Collectors.toList());
        System.out.println(userList +"==============");
    }



    @Test
    public void testStatic(){
        LoginServiceImpl.getInstance(ybUserMapper).getUsername2();
    }


}
