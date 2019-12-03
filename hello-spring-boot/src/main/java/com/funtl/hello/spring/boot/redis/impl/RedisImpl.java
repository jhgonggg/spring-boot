package com.funtl.hello.spring.boot.redis.impl;


import com.funtl.hello.spring.boot.constant.SysConst;
import com.funtl.hello.spring.boot.redis.Cache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@Component("cache")
public class RedisImpl<K, V> implements Cache<RedisTemplate<K, V>> {

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
	public Double increment(String key, double delta) {
		return redisTemplate.opsForValue().increment(key, delta);
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
	public boolean setIfAbsent(String key, Object value) {
		return redisTemplate.opsForValue().setIfAbsent(key ,value);
	}

	@Override
	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public String getAsStr(String key) {
		Object value = redisTemplate.opsForValue().get(key);
		return Objects.isNull(value) ? StringUtils.EMPTY : value.toString();
	}

	@Override
	public Long deleteCacheWithPattern(String pattern) {
		if (StringUtils.isBlank(pattern)) {
			return 0L;
		}
//		if (SysConst.ALL.equals(pattern)){
//			throw new RuntimeException("不允许删除所有缓存");
//		}
		Set<String> keys = redisTemplate.keys(pattern.concat(SysConst.ALL));
		return redisTemplate.delete(keys);
	}
	
	@Override
	public long getExpire(String key) {
		return redisTemplate.getExpire(key);
	}

	@Override
	public void expire(String key, long timeout, TimeUnit timeUnit) {
		redisTemplate.expire(key, timeout, timeUnit);
	}

	@Override
	public Object getAndSet(String key, Object value) {
		return redisTemplate.opsForValue().getAndSet(key, value);
	}

	@Override
	public boolean hasKey(String key){
		return redisTemplate.hasKey(key);
	}

	@Override
	public Boolean delete(String key) {
		Boolean bool = redisTemplate.delete(key);
		log.info("删除缓存, key-->{}, bool-->{}", key, bool);
		return bool;
	}

	@Override
	public void multiSet(Map<String, Object> paramMap) {
		if (isEmpty(paramMap)) {
			return;
		}
		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			redisTemplate.opsForValue().set(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public Map<String, Object> multiGet(Set<String> keys) {
		Map<String, Object> resultMap = new HashMap<>();
		if (isEmpty(keys)) {
			return Collections.EMPTY_MAP;
		}
		for (String key : keys) {
			resultMap.put(key, redisTemplate.opsForValue().get(key));
		}
		return resultMap;
	}

	@Override
	public  Long sadd(String key, Object... value) {
		return redisTemplate.boundSetOps(key).add(value);
	}
	
	@Override
	public Set<Object> members(String key){
		return redisTemplate.boundSetOps(key).members();
	}

	@Override
	public void hset(String key, String field, Object value){
		 redisTemplate.opsForHash().put(key,field,value);
	}

	@Override
	public void hsets(String key, Map<String,? extends Object> map){
		redisTemplate.opsForHash().putAll(key,map);
	}

	@Override
	public Object hget(String key, String field){
		return redisTemplate.opsForHash().get(key,field);
	}
	
	@Override
	public Long hdel(String key, String field){
		return redisTemplate.opsForHash().delete(key,field);
	}

	
	@Override
	public Long hdels(String key, String[] fields){
		return redisTemplate.opsForHash().delete(key,fields);
	}

	@Override
	public Map<String,Object> hashGetAll(String key){
		return redisTemplate.opsForHash().entries(key);
	}

	@Override
	public RedisTemplate<K, V> getCore() {
		return redisTemplate;
	}
}