package com.funtl.hello.spring.boot.init;

import com.funtl.hello.spring.boot.plusRedis.redis.Cache;
import com.funtl.hello.spring.boot.redis.RedisKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * description
 */
@Component
@Scope("prototype")
@Slf4j
public class ReviewerPersonThread extends Thread {

    @Autowired
    private Cache cache;

//    @Autowired
//    private WorkFlowWrapService workFlowWrapService;

    @Override
    public void run() {

        log.info("编审自动抄送线程启动成功");
        /*
         * 一台机一个线程，慢慢入库。
         */
        String thread = Thread.currentThread().getName();
        while (true) {
            try {
                String data = cache.blockRightPop(RedisKey.REVIEWER_PERSON_LIST, 30, TimeUnit.SECONDS);
                if (StringUtils.isNoneBlank(data)) {
                    long start = System.currentTimeMillis();
//                    PersonFormDTO person = JSON.parseObject(data, PersonFormDTO.class);
//                    workFlowWrapService.insertPersonWorkBatchSync(person.getUserIds(), person.getFormId());
//                    log.info("队列获取到元素并入库成功formId-->{}, time-->{}, thread-->{}", person.getFormId(), System.currentTimeMillis() - start, thread);
                }
//                RedisManager.publish(RedisKey.REDIS_COLUMN_CACHE_CHANNEL,"8887");
            } catch (Exception e) {
                log.error("编审个人数据入库异常-->{}, thread-->{}", e, thread);
            }
        }
    }
}
