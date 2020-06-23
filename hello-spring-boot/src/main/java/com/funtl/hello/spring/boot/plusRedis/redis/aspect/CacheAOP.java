package com.funtl.hello.spring.boot.plusRedis.redis.aspect;

import com.alibaba.fastjson.JSON;
import com.funtl.hello.spring.boot.constant.SysConst;
import com.funtl.hello.spring.boot.help.FunctionWrap;
import com.funtl.hello.spring.boot.plusRedis.redis.Cache;
import com.funtl.hello.spring.boot.plusRedis.redis.annotation.DeleteCache;
import com.funtl.hello.spring.boot.plusRedis.redis.annotation.GetCache;
import com.funtl.hello.spring.boot.plusRedis.redis.enums.CacheParamEnum;
import com.funtl.hello.spring.boot.plusRedis.redis.parser.RedisKeyParser;
import com.funtl.hello.spring.boot.response.MsgCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 缓存切面的逻辑实现  （common）
 */
@Aspect
@Component
@Slf4j
public class CacheAOP {

	@Autowired
	private Cache cache;
	@Autowired
	private RedisKeyParser keyParser;

	@Pointcut("@annotation(com.funtl.hello.spring.boot.plusRedis.redis.annotation.GetCache)")
	public void getCache(){
	}

	@Pointcut("@annotation(com.funtl.hello.spring.boot.plusRedis.redis.annotation.DeleteCache)")
	public void expireCache(){
	}

	@Around("getCache()")
	public Object aroundGet(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("缓存======");
		MethodSignature ms=(MethodSignature) joinPoint.getSignature();
		Method method = ms.getMethod();
		CacheParamEnum params = method.getAnnotation(GetCache.class).param();
		Object[] args = joinPoint.getArgs();
		String[] placeholders = method.getAnnotation(GetCache.class).placeholders();
		String prefix = params.getPrefix();
		if (ArrayUtils.isEmpty(placeholders) && StringUtils.isBlank(prefix)) {
			throw new RuntimeException("parameterless method is not allowed!");
		}
		String cacheKey = prefix + this.assemblePlaceholdersKey(ms, args, placeholders);
		Object object = cache.get(cacheKey);
		if (Objects.nonNull(object)) {
			return object;
		}

		Object proceed;
		synchronized (this) {
			proceed = joinPoint.proceed();
		}
		if (Objects.isNull(proceed)) {
			return null;
		}
		long expire = params.getExpire();
		if (expire == -1) {
			cache.set(cacheKey, proceed);
		} else {
			cache.set(cacheKey, proceed, expire, TimeUnit.SECONDS);
		}
		return proceed;
	}

	@After("expireCache()")
	public void afterExpire(JoinPoint joinPoint) {
		MethodSignature ms=(MethodSignature) joinPoint.getSignature();
		Method method = ms.getMethod();
		String prefix = method.getAnnotation(DeleteCache.class).prefix();
		String[] keys = method.getAnnotation(DeleteCache.class).key();
		Object[] args = joinPoint.getArgs();
        String cacheKey = prefix + this.assemblePlaceholdersKey(ms, args, keys);
		cache.delete(cacheKey);
	}

	/**
	 * 组装key元素
	 * @param methodSignature 当前请求方法签名信息
	 * @param args 当前请求方法参数列表
	 * @param placeholders 缓存注解 keys 方法参数的占位符
	 * @return 拼接的参数占位符字符串
	 */
	private String assemblePlaceholdersKey(MethodSignature methodSignature, Object[] args, String[] placeholders) {
		if (ArrayUtils.isEmpty(placeholders)) {
			return StringUtils.EMPTY;
		}
		//所有都是占位变量并且参数列表为空...
		if (Stream.of(placeholders).allMatch(e -> keyParser.isVar(e)) && ArrayUtils.isEmpty(args)) {
			log.error("方法-->{}, 组装rediskey异常-->{}", methodSignature.getName(), JSON.toJSONString(placeholders));
			throw new RuntimeException(MsgCode.FAIL.getMsg());
		}
		String key = Stream.of(placeholders).map(FunctionWrap.wrap(e -> keyParser.parse(e, args, methodSignature)))
				.filter(StringUtils::isNoneBlank).collect(Collectors.joining(SysConst.DOT));
//		if (isBlank(key)) {
//			log.error("方法-->{}, 组装rediskey异常-->{}", methodSignature.getName(), JSON.toJSONString(placeholders));
//			throw new RuntimeException(MsgCode.SYSTEM_ERROR, MsgCode.FAIL.getMsg());
//		}
		return StringUtils.isNoneBlank(key) ? key : StringUtils.EMPTY;
	}
}