package com.funtl.hello.spring.boot.plusRedis.redis;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface Cache {

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
	 */
	Long decr(String key);

	/**
	 * 设置键值对
	 */
	void set(String key, Object value);

	/**
	 * 设置带有过期时间的redis key
	 */
	void set(String key, Object value, long timeout, TimeUnit timeUnit);


	/**
	 * 获取键对应的值
	 */
	Object get(String key);


	/**
	 * 对键设置过期时间
	 */
	void expire(String key, long timeout, TimeUnit timeUnit);

	/**
	 * 删除
	 */
	Boolean delete(String key);

	/**
	 * 通配符删除
	 */
	Long deleteCacheWithPattern(String pattern);

	/**
	 * 根据分数获取sorted set 成员 带分数返回
	 * @param key zsetd key
	 * @param start 开始位置
	 * @param end 结束位置
	 */
	Set<ZSetOperations.TypedTuple<String>> zrangeWithScores(String key, int start, int end);

	/**
	 * Sorted Sets添加一个成员
	 * @param key redis的key
	 * @param value 对应的值
	 * @param score 分数
	 */
	Boolean zadd(String key, Object value, double score);

	/**
	 * 获取zset成员数
	 */
	Long zCard(Object key);

	/**
	 * 根据索引获取相关成员
	 */
	Set<String> range(String key, int start, int end);

	/**
	 * 删除一个zset成员
	 */
	Long zremove(String key, Object... members);
}
