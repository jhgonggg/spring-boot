package com.funtl.hello.spring.boot.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.funtl.hello.spring.boot.dto.UserDTO;
import com.funtl.hello.spring.boot.dto.UserVO;
import com.funtl.hello.spring.boot.entity.YbUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class HelloController {


    @GetMapping(value = "hi")
    public UserVO hello(UserDTO dto) {
        JSONObject content = JSON.parseObject(JSON.toJSONString(dto));
        JSONArray array = JSONArray.parseArray(dto.getInfo());
        array.forEach(e -> {
            System.out.println(e);
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(e));
            YbUser ybUser = JSON.parseObject(JSON.toJSONString(e), YbUser.class);
            System.out.println(ybUser);
            System.out.println(jsonObject);
        });
        System.out.println(array);
        content.put("info", CollectionUtil.isNotEmpty(array) ? array : new ArrayList());
        return UserVO.builder().content(content).build();
    }

    @GetMapping(value = "/a/{a}")
    public void getString(@PathVariable(value = "a") Integer b) {
        System.out.println(b);
//        Class<YbUser> entityClass = (Class<YbUser>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//
//        System.out.println(entityClass);

    }


}
