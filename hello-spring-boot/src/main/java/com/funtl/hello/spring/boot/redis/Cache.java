package com.funtl.hello.spring.boot.redis;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface Cache<T> {

	/**
	 * 追加内容
	 * @param key
	 * @param value
	 */
	void append(String key, String value);

	/**
	 * 增长
	 * @param key
	 * @param delta
	 */
	Long increment(String key, long delta);

	/**
	 * 增长
	 * @param key
	 */
	Long decr(String key);

	/**
	 * 增长
	 * @param key
	 * @param delta
	 */
	Double increment(String key, double delta);

	/**
	 * 设置键值对
	 * @param key
	 * @param value
	 */
	void set(String key, Object value);

	/**
	 * 设置带有过期时间的键值对
	 * @param key
	 * @param value
	 * @param timeout
	 * @param timeUnit
	 */
	void set(String key, Object value, long timeout, TimeUnit timeUnit);

	/**
	 * 仅当不存在时设置键值对
	 * @param key
	 * @param value
	 * @return
	 */
	boolean setIfAbsent(String key, Object value);

	/**
	 * 获取键对应的值
	 * @param key
	 * @return
	 */
	Object get(String key);

	/**
	 * 获取键对应的值
	 * 返回String
	 * @param key
	 * @return
	 */
	String getAsStr(String key);

	/**
	 * 获取键对应的过期时间
	 * @param key
	 * @return
	 */
	long getExpire(String key);

	/**
	 * 对键设置过期时间
	 * @param key
	 * @param timeout
	 * @param timeUnit
	 */
	void expire(String key, long timeout, TimeUnit timeUnit);

	/**
	 * 获取键对应的值，并赋予新值
	 * @param key
	 * @param value
	 * @return
	 */
	Object getAndSet(String key, Object value);

	/**
	 * 查询是否含有该键
	 * @param key
	 * @return
	 */
	boolean hasKey(String key);

	/**
	 * 删除键
	 * @param key
	 */
	Boolean delete(String key);

	/**
	 * 批量设置键值对
	 * @param paramMap
	 */
	void multiSet(Map<String, Object> paramMap);

	/**
	 * 批量获取键值对
	 * @param keys
	 * @return
	 */
	Map<String, Object> multiGet(Set<String> keys);

	Long sadd(String key, Object... value);

	Set<Object> members(String key);

	boolean existMember(String key ,Object value);

	void hset(String key, String field, Object value);
	void hsets(String key, Map<String, ? extends Object> map);
	Object hget(String key, String field);
	Long hdel(String key, String field);

	Long hdels(String key, String[] fields);

	Map<String,Object> hashGetAll(String key);

	/**
	 * 获取底层操作类
	 * @return
	 */
	T getCore();

	/**
	 * 通配符删除
	 * @param pattern
	 */
	Long deleteCacheWithPattern(String pattern);
}
