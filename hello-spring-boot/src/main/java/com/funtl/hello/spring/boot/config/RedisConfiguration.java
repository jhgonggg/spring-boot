package com.funtl.hello.spring.boot.config;

import com.funtl.hello.spring.boot.configs.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ description: 针对 RedisManager 中的 jedisPool
 */
@Configuration
public class RedisConfiguration {

    @Autowired
    private RedisConfig redisConfig;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(redisConfig.getMaxTotal());
        //最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdle());
        jedisPoolConfig.setMinIdle(redisConfig.getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(10000);
        //在获取连接的时候检查有效性, 默认false
        jedisPoolConfig.setTestOnBorrow(true);
        // 还回线程池时进行扫描
        jedisPoolConfig.setTestOnReturn(true);
        //是否开启空闲资源监测, 默认false
        jedisPoolConfig.setTestWhileIdle(true);
        //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(-1);
        //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(5000);
        //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(30000);
        //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(30000);
        //连接耗尽时是否阻塞, false报异常,true阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(true);
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig) {
        return new JedisPool(jedisPoolConfig, redisConfig.getHost(), redisConfig.getPort(),
                3000, redisConfig.getPassword(), redisConfig.getDatabase());
    }


}
