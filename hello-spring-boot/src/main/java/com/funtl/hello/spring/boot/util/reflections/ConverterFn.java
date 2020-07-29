package com.funtl.hello.spring.boot.util.reflections;

import java.io.Serializable;

/**
 * @author qy
 * @date 2020/7/29 16:40
 * @description
 */
@FunctionalInterface
public interface ConverterFn<T> extends Serializable {
    Object apply(T source);
}
