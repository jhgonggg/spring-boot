package com.funtl.hello.spring.boot.controller;

import com.funtl.hello.spring.boot.quartz.QuartzJobManager;
import com.funtl.hello.spring.boot.quartz.TestQuartz;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qy
 * @date 2019/9/30 14:53
 * @description
 */
@RestController
@RequestMapping
public class TestQuartzController {

    @GetMapping("/task")
    public void task() throws Exception {
        Map<String, Object> map=new HashMap<>();
        map.put("id",2);
        map.put("userId","小米");
        map.put("content","不错");
        QuartzJobManager.getInstance().addJob(TestQuartz.class,"test1", "testQuartz", "*/1 * * * * ?",map);
    }

    @GetMapping("/task2")
    public void task2() throws Exception {
        Map<String, Object> map=new HashMap<>();
        map.put("id",3);
        map.put("userId","小米33");
        map.put("content","不错33");
        QuartzJobManager.getInstance().addJob(TestQuartz.class,"test2", "testQuartz", "*/1 * * * * ?",map);
    }

}
