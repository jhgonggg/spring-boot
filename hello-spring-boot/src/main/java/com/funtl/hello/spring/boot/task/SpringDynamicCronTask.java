package com.funtl.hello.spring.boot.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author qy
 * @date 2019/12/23 17:46
 * @description
 */
@Slf4j
@EnableScheduling
@Component
public class SpringDynamicCronTask implements SchedulingConfigurer {

    public static String cron ="0 0 5 31 7 ?";


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        Runnable task = () -> {
            //任务逻辑代码部分.
            System.out.println("test Task");
            log.info("TaskCronChange task is running ... " + new Date());
        };

        Trigger trigger = triggerContext -> {

            //任务触发，可修改任务的执行周期.
            CronTrigger trigger1 = new CronTrigger(cron);
            Date nextExec = trigger1.nextExecutionTime(triggerContext);
            return nextExec;
        };

        taskRegistrar.addTriggerTask(task, trigger);
    }


}
