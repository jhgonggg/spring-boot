package com.funtl.hello.spring.boot.plusRedis.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 删除缓存注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DeleteCache {

	/**
	 * 缓存中key的前缀
	 */
	String prefix();

	/**
	 * 指定的key的字段
	 */
	String[] key() default {};
}