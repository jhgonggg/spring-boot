package com.funtl.hello.spring.boot.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
        System.out.println(Arrays.toString(keys));
        int id = dataMap.getInt("id");
        String content = dataMap.getString("content");
        String userId = dataMap.getString("userId");
        System.out.println(id);
//        System.out.println(content);
        System.out.println(userId);

        //  QuartzJobManager.getInstance().jobdelete(this.getClass().getSimpleName(),"ah");//执行完此任务就删除自己
    }
}
