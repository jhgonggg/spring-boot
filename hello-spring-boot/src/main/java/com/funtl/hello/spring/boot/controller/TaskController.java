package com.funtl.hello.spring.boot.controller;

import com.funtl.hello.spring.boot.task.SpringDynamicCronTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qy
 * @date 2019/12/23 17:44
 * @description
 */
@RestController
public class TaskController {

    @GetMapping(value = "task1")
    public void testTask1(String cron){
        SpringDynamicCronTask.cron = cron ;
    }
}
