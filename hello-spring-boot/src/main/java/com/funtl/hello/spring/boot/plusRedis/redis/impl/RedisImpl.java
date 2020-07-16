package com.funtl.hello.spring.boot.plusRedis.redis.impl;

import com.funtl.hello.spring.boot.constant.SysConst;
import com.funtl.hello.spring.boot.plusRedis.redis.Cache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component("cache")
public class RedisImpl<K, V> implements Cache {

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public void append(String key, String value) {
		redisTemplate.opsForValue().append(key, value);
	}

	@Override
	public Long increment(String key, long delta) {
		return redisTemplate.opsForValue().increment(key, delta);
	}

	@Override
	public Long decr(String key) {
		return redisTemplate.opsForValue().decrement(key);
	}

	@Override
	public void set(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public void set(String key, Object value, long timeout, TimeUnit timeUnit) {
		redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
	}

	@Override
	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public Long leftPush(String key, String value) {
		Long ret = redisTemplate.opsForList().leftPush(key, value);
		log.info("添加到队列, key-->{}, value-->{}", key, value);
		return ret;
	}

	@Override
	public String blockRightPop(String key, long timeout, TimeUnit unit) {
		try {
			Object ret = redisTemplate.opsForList().rightPop(key, timeout, unit);
			return Objects.nonNull(ret) ? ret.toString() : null;
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public List<String> lrange(String key, long start, long end) {
		return redisTemplate.opsForList().range(key, start, end);
	}

	@Override
	public Long deleteCacheWithPattern(String pattern) {
		if (StringUtils.isBlank(pattern)) {
			return 0L;
		}
//		if (SysConst.ALL.equals(pattern)) {
//			throw new RuntimeException("不允许删除所有缓存");
//		}
		Set<String> keys = redisTemplate.keys(pattern.concat(SysConst.ALL));
		return redisTemplate.delete(keys);
	}

	@Override
	public Boolean zadd(String key, Object value, double score) {

		return redisTemplate.opsForZSet().add(key, value, score);
	}

	@Override
	public Long zCard(Object key) {
		return redisTemplate.opsForZSet().zCard(key);
	}

	@Override
	public Set<String> range(String key, int start, int end) {
		return redisTemplate.opsForZSet().range(key, start, end);
	}

	@Override
	public Long zremove(String key, Object... members) {
		return redisTemplate.opsForZSet().remove(key, members);
	}

	@Override
	public Set<ZSetOperations.TypedTuple<String>> zrangeWithScores(String key, int start, int end) {
		return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
	}

	@Override
	public void expire(String key, long timeout, TimeUnit timeUnit) {
		redisTemplate.expire(key, timeout, timeUnit);
	}


	@Override
	public Boolean delete(String key) {
		Boolean bool = redisTemplate.delete(key);
		log.info("删除缓存, key-->{}, bool-->{}", key, bool);
		return bool;
	}
}