package com.funtl.hello.spring.boot.redis;

import com.funtl.hello.spring.boot.util.SpringContextHolder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author qy
 * @date 2019/12/27 17:59
 * @description
 */
public class RedisManager {

    private static JedisPool jedisPool = null;
    private static int minute1 = 60; // 设置Key的过期时间，1分钟
    private static int hour1 = minute1 * 60; // 设置Key的过期时间，1小时
    private static int day1 = hour1 * 24; // 设置Key的过期时间，1天
    private static int week1 = day1 * 7; // 1周

    private static JedisPool getJedisPool() {
        if (jedisPool == null) {
            jedisPool = SpringContextHolder.getBean("jedisPool", JedisPool.class);
        }
        return jedisPool;
    }

    public static Jedis getJedis() {
        return getJedisPool().getResource();
    }

    public static void returnResource(Jedis resource) {
        getJedisPool().returnResource(resource);
    }
}
