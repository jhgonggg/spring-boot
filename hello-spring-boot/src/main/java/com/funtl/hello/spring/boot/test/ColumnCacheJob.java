package com.funtl.hello.spring.boot.test;

import com.funtl.hello.spring.boot.redis.ColumnCacheSubscriber;
import com.funtl.hello.spring.boot.redis.RedisKey;
import com.funtl.hello.spring.boot.redis.RedisManager;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Description:
 */
@Slf4j
public class ColumnCacheJob {

    // 仅清除JVM缓存
    private static final int CLEAR_CACHE_TYPE = 1;

    // 清除JVM缓存和Redis缓存
    private static final int CLEAR_CACHE_AND_REDIS = 2;

    /**
     * 栏目缓存队列 是一个基于链接节点的无界线程安全队列，它采用先进先出的规则对节点进行排序
     */
    private static final ConcurrentLinkedQueue<ColumnCacheTask> COLUMN_CACHE_TASK_BLOCKING_QUEUE = new ConcurrentLinkedQueue<>();

    /**
     * CacheManager
     */
//    private static BaseCache BASECACHE = null;

    /**
     * 初始化
     */
    public static void init() {
        log.info("栏目缓存线程:启动");
        Thread columnCacheThread = new Thread(() -> {
            while (Boolean.TRUE) {
                try {
                    // poll():当没有获得数据时返回为null，如果有数据时则移除移除表头数据，并将表头数据进行返回
                    ColumnCacheTask columnCacheTask = COLUMN_CACHE_TASK_BLOCKING_QUEUE.poll();
                    if (columnCacheTask != null) {
//                            if (BASECACHE == null) {
//                                BASECACHE = (BaseCache) CacheManager.find(ColumnCache.class);
//                            }

                        long l = System.currentTimeMillis();
                        log.info("栏目缓存线程:清除缓存开始");
                        clearCache(columnCacheTask);
                        long l1 = System.currentTimeMillis();
                        log.info("栏目缓存线程:清除缓存完成,耗时={}", (l1 - l));
                        log.info("栏目缓存线程:未处理任务={}", COLUMN_CACHE_TASK_BLOCKING_QUEUE.size());
                    } else {
                        synchronized (COLUMN_CACHE_TASK_BLOCKING_QUEUE) {
                            COLUMN_CACHE_TASK_BLOCKING_QUEUE.wait();
                        }
                    }
//                    } catch (E5Exception e) {
//                        log.error("栏目缓存线程:清理栏目缓存异常", e);
                } catch (Exception e) {
                    log.error("栏目缓存线程异常", e);
                }
            }
        });
        columnCacheThread.setName("column-cache-thread-");
        columnCacheThread.start();

        log.info("订阅e5缓存线程启动");
        Thread e5CacheThread = new Thread(() -> {
            ColumnCacheSubscriber columnCacheSubscriber = new ColumnCacheSubscriber();
            RedisManager.subscribe(columnCacheSubscriber, RedisKey.REDIS_COLUMN_CACHE_CHANNEL);
        });
        e5CacheThread.setName("e5-cache-fresh-thread-");
        e5CacheThread.start();
    }

    /**
     * 唤醒
     */
    public static void columnCacheQueueNotifyAll() {
        synchronized (COLUMN_CACHE_TASK_BLOCKING_QUEUE) {
            COLUMN_CACHE_TASK_BLOCKING_QUEUE.notifyAll();
        }
    }

    public static void addTask(int docLibId) {
        log.info("添加清除栏目缓存任务,type={}", CLEAR_CACHE_TYPE);
        RedisManager.publish(RedisKey.REDIS_COLUMN_CACHE_CHANNEL, String.valueOf(System.currentTimeMillis()));
        COLUMN_CACHE_TASK_BLOCKING_QUEUE.add(new ColumnCacheTask(CLEAR_CACHE_TYPE, docLibId));
    }

    public static void addTask(int docLibId, long columnId, long parentId, long grandFatherId) {
        log.info("添加清除栏目缓存任务,type={}", CLEAR_CACHE_AND_REDIS);
        RedisManager.publish(RedisKey.REDIS_COLUMN_CACHE_CHANNEL, String.valueOf(System.currentTimeMillis()));
        COLUMN_CACHE_TASK_BLOCKING_QUEUE.add(new ColumnCacheTask(CLEAR_CACHE_AND_REDIS, docLibId, columnId, parentId, grandFatherId));
    }

    private static void clearCache(ColumnCacheTask columnCacheTask) throws Exception {
        switch (columnCacheTask.getTaskType()) {
            case CLEAR_CACHE_TYPE:
//                clearCacheOnly(columnCacheTask);
                break;
            case CLEAR_CACHE_AND_REDIS:
//                clearCacheAndRedis(columnCacheTask);
                break;
        }
    }

//    private static void clearCacheOnly(ColumnCacheTask columnCacheTask) throws E5Exception {
//        BASECACHE.refresh(columnCacheTask.getDocLibId());
//    }

//    private static void clearCacheAndRedis(ColumnCacheTask columnCacheTask) throws E5Exception {
//        BASECACHE.refresh(columnCacheTask.getDocLibId());
//        long columnId = columnCacheTask.getColumnId();
//        long parentId = columnCacheTask.getParentId();
//        long grandFatherId = columnCacheTask.getGrandFatherId();
//        if (columnId > 0) {
//            clearColumnRedisCache(columnId);
//        }
//        if (parentId >= 0) {
//            clearColumnRedisCache(parentId);
//        }
//        if (grandFatherId >= 0) {
//            clearColumnRedisCache(grandFatherId);
//        }
//    }

//    private static void clearColumnRedisCache(long columnId) {
//        RedisManager.clear(RedisKey.APP_COLLIST_KEY + "site1." + columnId);
//        RedisManager.clear(RedisKey.APP_COLLIST_KEY + columnId);
//        RedisManager.clear(RedisKey.SERVICE_COLUMN_KEY + columnId);
//        RedisManager.clear(RedisKey.SERVICE_TOWN_COLUMN_V1_KEY + columnId);
//        RedisManager.clear(RedisKey.SERVICE_TOWN_COLUMN_KEY + columnId);
//        RedisManager.clear(RedisKey.TOWN_COLUMN_KEY + columnId);
//    }

    public void destroy() {

    }


}

class ColumnCacheTask {

    private int taskType;

    private int docLibId;

    private long columnId;

    private long parentId;

    private long grandFatherId;

    public ColumnCacheTask(int taskType, int docLibId) {
        this.taskType = taskType;
        this.docLibId = docLibId;
    }

    public ColumnCacheTask(int taskType, int docLibId, long columnId, long parentId, long grandFatherId) {
        this.taskType = taskType;
        this.docLibId = docLibId;
        this.columnId = columnId;
        this.parentId = parentId;
        this.grandFatherId = grandFatherId;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public int getDocLibId() {
        return docLibId;
    }

    public void setDocLibId(int docLibId) {
        this.docLibId = docLibId;
    }

    public long getColumnId() {
        return columnId;
    }

    public void setColumnId(long columnId) {
        this.columnId = columnId;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getGrandFatherId() {
        return grandFatherId;
    }

    public void setGrandFatherId(long grandFatherId) {
        this.grandFatherId = grandFatherId;
    }
}


