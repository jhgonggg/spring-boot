package com.funtl.hello.spring.boot.plusRedis.redis.parser;

import org.aspectj.lang.reflect.MethodSignature;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

public interface RedisKeyParser {

	/**
	 * 解析参数
	 * @param key
	 * @return
	 */
	String parse(String key, Object[] args, MethodSignature methodSignature) throws IntrospectionException, InvocationTargetException, IllegalAccessException;

	/**
	 * 判断是否是变量占位
	 * @param key
	 * @return
	 */
	boolean isVar(String key);
}
