package com.funtl.hello.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = {"","index","/"})
//    @AccessLimit(seconds = 5, maxCount = 20, needLogin = true)
    public String index(Model model){
        model.addAttribute("message",null);
        return "login" ;
    }

    @GetMapping(value = {"people"})
//    @AccessLimit(seconds = 5, maxCount = 20, needLogin = true)
    public String vue(Model model){
        return "people";
    }

    @GetMapping(value = {"main"})
    public String main(Model model){
        return "main";
    }
}
