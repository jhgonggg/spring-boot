package com.funtl.hello.spring.boot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funtl.hello.spring.boot.constant.SysConst;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;


@Configuration
public class RedisTemplateConfiguration {

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {

        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(objectMapper.copy().enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL));
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(genericJackson2JsonRedisSerializer);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair)
                .entryTtl(Duration.ofSeconds(SysConst.EXPIRE_DEFAULT));
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = Maps.newHashMap();
        redisCacheConfigurationMap.put("test", RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(SysConst.EXPIRE_DEFAULT)));


        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(Objects.requireNonNull(redisTemplate.getConnectionFactory())),
                // 默认策略，未配置的 key 会使用这个
                redisCacheConfiguration,
                // 指定 key 策略
                redisCacheConfigurationMap
        );
    }

    @Bean
    public RedisTemplate getRedisTemplate(RedisTemplate redisTemplate) {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(objectMapper.copy().enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL));
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        return redisTemplate;
    }

}
