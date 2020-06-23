package com.funtl.hello.spring.boot.redis;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

/**
 * Description:Redis分布式锁
 *
 */
public class RedisLock {

    private static final Logger log = LoggerFactory.getLogger(RedisLock.class);

    private static final String LOCK_SUCCESS = "OK";

    private static final String RELEASE_LOCK_LUA_SCRIPT = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";

    /**
     * 获取锁
     *
     * @param key        锁
     * @param requestId  请求id
     * @param expireTime 过期时间
     * @return
     */
    public static boolean attemptLock(String key, String requestId, long expireTime) {
        String result = RedisManager.set(key, requestId, NxXx.NX.name(), ExPx.PX.name(), expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 解锁
     *
     * @param key       解锁
     * @param requestId 请求id
     * @return
     */
    public static boolean unlock(String key, String requestId) {
        Object result = RedisManager.eval(RELEASE_LOCK_LUA_SCRIPT, Collections.singletonList(key), Collections.singletonList(requestId));
        if (NumberUtils.LONG_ONE.equals(result)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


}

/**
 * set key
 */
enum NxXx {
    // Only set the key if it does not already exist
    NX,
    // Only set the key if it already exist
    XX
}

/**
 * expire time units
 */
enum ExPx {
    // seconds
    EX,
    // milliseconds
    PX
}