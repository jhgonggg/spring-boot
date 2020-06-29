package com.funtl.hello.spring.boot.redis;

import com.funtl.hello.spring.boot.test.E5SiteConfigJob;
import redis.clients.jedis.JedisPubSub;

/**
 * Description:
 */
public class ColumnCacheSubscriber extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        E5SiteConfigJob.e5CacheQueueNotifyAll();
        E5SiteConfigJob.addTask(message);
    }
}
