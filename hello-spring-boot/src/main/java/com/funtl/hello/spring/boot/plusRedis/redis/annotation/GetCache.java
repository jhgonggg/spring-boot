package com.funtl.hello.spring.boot.plusRedis.redis.annotation;


import com.funtl.hello.spring.boot.plusRedis.redis.enums.CacheParamEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface GetCache {

	/**
	 * 缓存相关参数设置
	 * @return 缓存枚举
	 */
	CacheParamEnum param();

	/**
	 * 参数占位符 placeholders
	 */
	 String[] placeholders() default {};
}
