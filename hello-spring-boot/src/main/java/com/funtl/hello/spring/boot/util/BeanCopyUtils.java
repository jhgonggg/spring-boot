package com.funtl.hello.spring.boot.util;

import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BeanCopyUtils extends org.springframework.beans.BeanUtils {


    public static <T> List<T> copyList(List<?> sourceList, Class<T> clazz) {

        Assert.notNull(sourceList, "sourceList must not be null");

        List<T> list = new ArrayList<>();
        for (Object obj : sourceList) {
            try {
                T t = clazz.newInstance();
                copyProperties(obj, t);
                list.add(t);
            } catch (Exception e) {
                e.printStackTrace();
                throw new FatalBeanException(
                        "Could not copy list '" + clazz.getName() + "' from source to target", e);
            }
        }
        return list;
    }

    public static void copyProperties(Object source, Object target)
            throws BeansException {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();

        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null &&
                            ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            //只拷贝不为null的属性
                            if (value != null) {
                                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                    writeMethod.setAccessible(true);
                                }
                                writeMethod.invoke(target, value);
                            }
                        } catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }

    public static <T> Map<String, String> bean2Map(T source) throws IllegalAccessException {
        Map<String, String> result = Maps.newHashMap();

        Class<?> sourceClass = source.getClass();
        //拿到所有的字段,不包括继承的字段
        Field[] sourceFiled = sourceClass.getDeclaredFields();
        for (Field field : sourceFiled) {
            //设置可访问,不然拿不到private
            field.setAccessible(true);
            //配置了注解的话则使用注解名称,作为header字段
            result.put(field.getName(), String.valueOf(field.get(source)));
        }
        return result;
    }
}
