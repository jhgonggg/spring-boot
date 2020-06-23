package com.funtl.hello.spring.boot.plusRedis.redis.parser.impl;

import com.funtl.hello.spring.boot.constant.SysConst;
import com.funtl.hello.spring.boot.plusRedis.redis.parser.RedisKeyParser;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * @author HUGUANG 2019年09月11日 上午9:53:42
 */
@Component
public class RedisKeyParserImpl implements RedisKeyParser {

	/**
	 *
	 * @param key 缓存参数占位符
	 * @param args  请求方法参数列表
	 * @param methodSignature 方法签名
	 * @return 组装好的key
	 */
	@Override
	public String parse(String key, Object[] args, MethodSignature methodSignature) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
		if (StringUtils.isBlank(key) || !isVar(key)) {
			return StringUtils.EMPTY;
		}
		List<String> argNames = Arrays.asList(methodSignature.getParameterNames());
		if (!isFromField(key)) {
			key = key.substring(1);
			if (!argNames.contains(key)) {
				return StringUtils.EMPTY;
			}
			Object arg = args[argNames.indexOf(key)];
			return Objects.isNull(arg) ?
					StringUtils.EMPTY :
					ClassUtils.isAssignable(arg.getClass(), Iterable.class) ? Joiner.on(SysConst.COMMA).join((Iterable<?>) arg) : arg.toString();
		}

		String[] split = StringUtils.split(key, SysConst.DOT);
		String argName = split[0].substring(1);
		if (!argNames.contains(argName)) {
			return StringUtils.EMPTY;
		}
		Class[] argTypes = methodSignature.getParameterTypes();
		Object arg = args[argNames.indexOf(argName)];
		Class type = argTypes[argNames.indexOf(argName)];
		List<String> fields = Arrays.asList(split).subList(1, split.length);
		for (String field : fields) {
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field, type);
			arg = propertyDescriptor.getReadMethod().invoke(arg);
		}
		return Objects.isNull(arg) ? StringUtils.EMPTY : arg.toString();
	}

	/**
	 * 根据井号（#）判断是变量还是字符串。
	 */
	@Override
	public boolean isVar(String input){
		return StringUtils.startsWith(input, SysConst.VARIABLE_IDENTIFIER);
	}

	/**
	 * 根据点（.）判断是否包含有次级属性（准确应该是以 . 开头的缓存参数占位符视为下一级对象的某个属性）
	 */
	private boolean isFromField(String input){
		return StringUtils.contains(input, SysConst.DOT);
	}
}