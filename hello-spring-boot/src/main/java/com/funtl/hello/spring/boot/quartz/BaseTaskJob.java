package com.funtl.hello.spring.boot.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author qy
 * @date 2019/9/30 14:47
 * @description 基础任务调度taskJob接口，新建任务请继承此接口
 */
public interface BaseTaskJob extends Job {
    @Override
    void execute(JobExecutionContext context) throws JobExecutionException;
}
