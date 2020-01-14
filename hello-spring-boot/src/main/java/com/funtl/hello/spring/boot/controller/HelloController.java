package com.funtl.hello.spring.boot.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.funtl.hello.spring.boot.constant.SysConst;
import com.funtl.hello.spring.boot.dto.UserDTO;
import com.funtl.hello.spring.boot.vo.UserVO;
import com.funtl.hello.spring.boot.entity.YbUser;
import com.funtl.hello.spring.boot.help.ContentFormatter;
import com.funtl.hello.spring.boot.help.TokenFormatter;
import com.funtl.hello.spring.boot.mapper.YbUserMapper;
import com.funtl.hello.spring.boot.response.Response;
import com.funtl.hello.spring.boot.response.ResponseBuilder;
import com.funtl.hello.spring.boot.service.LoginService;
import com.funtl.hello.spring.boot.util.IpUtil;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HelloController {
    @Autowired
    private YbUserMapper ybUserMapper;
    @Autowired
    private LoginService loginService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new ContentFormatter(), "content");
        binder.addCustomFormatter(new TokenFormatter(), "userId");
    }

    private static final List<String> LIST = Lists.newArrayList();

    @GetMapping(value = "hi")
    @ApiResponses({@ApiResponse(code = 200, message = "请求成功", response = UserDTO.class)})
    public UserVO hello(UserDTO dto) {
        JSONObject content = JSON.parseObject(JSON.toJSONString(dto));
        JSONArray array = JSONArray.parseArray(dto.getInfo());
        array.forEach(e -> {
            System.out.println(e);
            // 1
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(e));
            // 2
            JSONObject json = (JSONObject) e;
            // 3
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
                    "    \"riskLevel\": \"REJECT\",\n";
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
        Example example = new Example(YbUser.class);
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

    @GetMapping(value = "ip")
    public void getIp(HttpServletRequest httpServletRequest) {
        System.out.println("ip--->" + IpUtil.getRemoteAddr(httpServletRequest));
        System.out.println(loginService.login(new YbUser()));
        System.out.println(IpUtil.getInternetIp());
    }

    @GetMapping(value = "getSortedList")
    public Mono<Response> getSortedList() {
        // 根据姓名从从大到小排序
        return Mono.fromCallable(() -> ResponseBuilder.buildSuccess(ybUserMapper.selectAll().stream().sorted(Comparator.comparing(YbUser::getUsername).reversed()).collect(Collectors.toList())));
    }
}
