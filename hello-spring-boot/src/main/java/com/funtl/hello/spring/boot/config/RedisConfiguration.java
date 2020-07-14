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
        jedisPoolConfig.setMaxTotal(redisConfig.getMaxTotal());
        jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdle());
        jedisPoolConfig.setMinIdle(redisConfig.getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(10000);
        jedisPoolConfig.setTestOnBorrow(false);
        jedisPoolConfig.setTestOnReturn(false);
        jedisPoolConfig.setTestWhileIdle(true);

        jedisPoolConfig.setNumTestsPerEvictionRun(-1);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(5000);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(30000);
        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(30000);
        jedisPoolConfig.setBlockWhenExhausted(false);
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig) {
        return new JedisPool(jedisPoolConfig, redisConfig.getHost(), redisConfig.getPort(),
                3000, redisConfig.getPassword(), redisConfig.getDatabase());
    }


}
