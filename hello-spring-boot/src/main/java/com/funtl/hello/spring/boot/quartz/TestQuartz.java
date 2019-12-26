package com.funtl.hello.spring.boot.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author qy
 * @date 2019/9/30 14:51
 * @description
 */
@Slf4j
@Service
public class TestQuartz implements BaseTaskJob {

    @Override
    public void execute(JobExecutionContext context)  {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String[] keys = dataMap.getKeys();
        int id = dataMap.getInt("id");
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        String content = dataMap.getString("content");
        String userId = dataMap.getString("userId");
        System.out.println(id);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(content);
//        System.out.println(userId);

        //  QuartzJobManager.getInstance().jobdelete(this.getClass().getSimpleName(),"ah");//执行完此任务就删除自己
    }
}
