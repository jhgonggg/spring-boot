package com.funtl.hello.spring.boot.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * @author qy
 * @date 2019/9/30 14:24
 * @description 解决quartz无法注入spring bean问题
 */
@Component
public class TaskJobFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;
    // 创建定时任务对象
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        //调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        //进行注入
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }

}
