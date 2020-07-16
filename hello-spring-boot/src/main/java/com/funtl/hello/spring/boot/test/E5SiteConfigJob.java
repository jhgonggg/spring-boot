package com.funtl.hello.spring.boot.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Description:e5缓存刷新:站点相关配置
 *
 */
@Component
@Slf4j
public class E5SiteConfigJob {

    /**
     * e5 cache class name list
     */
    private static List<String> CACHE_CLASS_NAME_LIST = new ArrayList<>(6);

    /**
     * e5缓存队列
     */
    private static final ConcurrentLinkedQueue<String> E5_CACHE_REFRESH_TASK_BLOCKING_QUEUE = new ConcurrentLinkedQueue<>();

    static {
        CACHE_CLASS_NAME_LIST.add("com.founder.xy.column.ColumnCache");
        CACHE_CLASS_NAME_LIST.add("com.founder.xy.set.ExtFieldCache");
        CACHE_CLASS_NAME_LIST.add("com.founder.xy.set.SourceCache");
        CACHE_CLASS_NAME_LIST.add("com.founder.xy.jpublish.BaseDataCache");
        CACHE_CLASS_NAME_LIST.add("com.founder.e5.sys.SysCache");

        Thread e5SiteConfigThread = new Thread(() -> {
            while (true) {
                try {
                    log.info("进入 E5SiteConfigJob.........");
                    String e5CacheTask = E5_CACHE_REFRESH_TASK_BLOCKING_QUEUE.poll();
                    if (StringUtils.isNotBlank(e5CacheTask)) {
                        long l = System.currentTimeMillis();
                        refreshCache();
                        long l1 = System.currentTimeMillis();
                        log.info("e5刷新缓存线程:刷新[站点相关配置]完成,耗时={}", (l1 - l));
                        log.info("e5刷新缓存线程:剩余={}", E5_CACHE_REFRESH_TASK_BLOCKING_QUEUE.size());

                        // 不为空时清空,再添加一个
                        if (CollectionUtils.isNotEmpty(E5_CACHE_REFRESH_TASK_BLOCKING_QUEUE)) {
                            E5_CACHE_REFRESH_TASK_BLOCKING_QUEUE.removeAll(E5_CACHE_REFRESH_TASK_BLOCKING_QUEUE);
                            addTask(NumberUtils.INTEGER_ONE.toString());
                        }
                    } else {
                        synchronized (E5_CACHE_REFRESH_TASK_BLOCKING_QUEUE) {
                            log.info("E5_CACHE_REFRESH_TASK_BLOCKING_QUEUE 进入等待状态");
                            E5_CACHE_REFRESH_TASK_BLOCKING_QUEUE.wait();
                        }
                    }
                } catch (Exception e) {
                    log.error("e5刷新线程异常", e);
                }
            }
        });
        e5SiteConfigThread.setName("e5-site-config-thread-");
        e5SiteConfigThread.start();
    }

    /**
     * 调用e5刷新缓存:站点相关配置
     */
    private static void refreshCache() throws Exception {
        for (String cacheClassName : CACHE_CLASS_NAME_LIST) {
//            CacheManager.refresh(cacheClassName);
        }
    }

    /**
     * 唤醒
     */
    public static void e5CacheQueueNotifyAll() {
        synchronized (E5_CACHE_REFRESH_TASK_BLOCKING_QUEUE) {
            log.info("唤醒 E5_CACHE_REFRESH_TASK_BLOCKING_QUEUE");
            E5_CACHE_REFRESH_TASK_BLOCKING_QUEUE.notifyAll();
        }
    }

    /**
     * 添加任务
     */
    public static void addTask(String message) {
        E5_CACHE_REFRESH_TASK_BLOCKING_QUEUE.add(message);
    }

}
