package com.funtl.hello.spring.boot.controller;

import com.funtl.hello.spring.boot.entity.YbUser;
import com.funtl.hello.spring.boot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "login")
    public String login(Model model, YbUser ybUser){
        YbUser login = loginService.login(ybUser);
        if(login==null){
            model.addAttribute("message","账号或密码错误");
            return "login";
        }
        return "main";
    }
}
