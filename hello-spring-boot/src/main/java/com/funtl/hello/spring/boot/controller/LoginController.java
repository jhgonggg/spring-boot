package com.funtl.hello.spring.boot.controller;

import cn.hutool.core.date.SystemClock;
import com.alibaba.fastjson.JSON;
import com.funtl.hello.spring.boot.bean.LoginToken;
import com.funtl.hello.spring.boot.entity.YbUser;
import com.funtl.hello.spring.boot.redis.Cache;
import com.funtl.hello.spring.boot.redis.RedisKey;
import com.funtl.hello.spring.boot.service.LoginService;
import com.funtl.hello.spring.boot.util.DesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private Cache cache;
    @Value("${login.valid.time}")
    private Long valid;

    @GetMapping(value = "login")
    public String login(Model model, YbUser ybUser, HttpServletRequest httpServletRequest) throws Exception {
        YbUser login = loginService.login(ybUser);
        if (Objects.isNull(login)) {
            model.addAttribute("message", "账号或密码错误");
            return "login";
        }
        LoginToken token = LoginToken.builder().timestamp(SystemClock.now()).userId(login.getUsername()).uuid(String.valueOf(login.getId())).build();
        // 加密
        String userToken = DesUtil.encrypt(JSON.toJSONString(token), DesUtil.KEY);
        cache.set(RedisKey.USER_LOGIN_KEY + login.getId(), userToken, valid, TimeUnit.MINUTES);
        cache.set(RedisKey.USER_SESSION_KEY + login.getId(), httpServletRequest.getSession().getId());
        // 返回 token 给前端
        model.addAttribute("token", userToken);
        return "main";
    }
}
