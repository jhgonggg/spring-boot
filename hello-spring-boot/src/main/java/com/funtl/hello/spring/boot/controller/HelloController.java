package com.funtl.hello.spring.boot.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.funtl.hello.spring.boot.constant.SysConst;
import com.funtl.hello.spring.boot.dto.UserDTO;
import com.funtl.hello.spring.boot.dto.UserVO;
import com.funtl.hello.spring.boot.entity.YbUser;
import com.funtl.hello.spring.boot.mapper.YbUserMapper;
import com.funtl.hello.spring.boot.response.Response;
import com.funtl.hello.spring.boot.response.ResponseBuilder;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HelloController {
    @Autowired
    private YbUserMapper ybUserMapper;


    private  static  final List<String> LIST = Lists.newArrayList();

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
            String data = "{\n" +
                    "    \"code\": 1100,\n" +
                    "    \"message\": \"成功\",\n" +
                    "    \"requestId\": \"4792a7f218913977d298d814b5c3a67e\",\n" +
                    "    \"riskLevel\": \"REJECT\",\n" +
                    "    \"score\": 800,\n" +
                    "    \"detail\": {\n" +
                    "        \"description\": \"高风险设备：设备标识为空\",\n" +
                    "        \"descriptionV2\": \"高风险设备：伪造设备\",\n" +
                    "        \"hits\": [\n" +
                    "            {\n" +
                    "                \"description\": \"高风险设备：设备标识为空\",\n" +
                    "                \"descriptionV2\": \"高风险设备：伪造设备\",\n" +
                    "                \"model\": \"M99020202\",\n" +
                    "                \"riskLevel\": \"REJECT\",\n" +
                    "                \"score\": 800\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"description\": \"高风险手机号：命中手机号黑库\",\n" +
                    "                \"descriptionV2\": \"高风险手机号：农场设备手机号\",\n" +
                    "                \"model\": \"M99040109\",\n" +
                    "                \"riskLevel\": \"REJECT\",\n" +
                    "                \"score\": 797\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"description\": \"高风险手机号：命中手机号黑库\",\n" +
                    "                \"descriptionV2\": \"高风险手机号：农场设备手机号\",\n" +
                    "                \"model\": \"M99040114\",\n" +
                    "                \"riskLevel\": \"REJECT\",\n" +
                    "                \"score\": 798\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"model\": \"M99020202\",\n" +
                    "        \"token\": {\n" +
                    "            \"groupId\": \"\",\n" +
                    "            \"groupSize\": 0,\n" +
                    "            \"riskGrade\": \"\",\n" +
                    "            \"riskReason\": \"\",\n" +
                    "            \"riskType\": \"\",\n" +
                    "            \"score\": 0\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
            JSONObject object = JSON.parseObject(data);
            LIST.add(object.getString("riskLevel"));
            return ResponseBuilder.buildSuccess(userDTO);
        });
    }

    @GetMapping(value = "list")
    public List<String> getList() {
       return LIST;
    }

    @GetMapping(value = "find")
    public String find(String name) {
        Example example=new Example(YbUser.class);
        example.createCriteria().andLike("username", SysConst.PERCENT.concat(name).concat(SysConst.PERCENT));
        List<YbUser> userList = ybUserMapper.selectByExample(example);
        List<JSONObject> collect = userList.stream().map(e -> {
            JSONObject object = new JSONObject();
            object.put("id", e.getId());
            object.put("name", e.getUsername());
            return object;
        }).collect(Collectors.toList());
        return collect.toString();
    }
}
