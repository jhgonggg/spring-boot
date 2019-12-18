package com.funtl.hello.spring.boot.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.funtl.hello.spring.boot.dto.UserDTO;
import com.funtl.hello.spring.boot.dto.UserVO;
import com.funtl.hello.spring.boot.entity.YbUser;
import com.funtl.hello.spring.boot.response.Response;
import com.funtl.hello.spring.boot.response.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@RestController
public class HelloController {


    @GetMapping(value = "hi")
    @ApiResponses({@ApiResponse(code = 200, message = "请求成功", response = UserDTO.class)})
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
    @ApiOperation(value = "请求", response = UserDTO.class)
    @ApiResponses({@ApiResponse(code = 200, message = "请求成功", response = UserDTO.class)})
    public Mono<Response> getString(@PathVariable(value = "a") String b) {
        return Mono.fromCallable(() -> {
//      Class<YbUser> entityClass = (Class<YbUser>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//      System.out.println(entityClass);
            UserDTO userDTO = new UserDTO();
            userDTO.setInfo("info");
            userDTO.setMessage(b);
            return ResponseBuilder.buildSuccess(userDTO);
        });
    }


}
