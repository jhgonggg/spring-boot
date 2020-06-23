package com.funtl.hello.spring.boot.help;

/**
 * Description: 声明函数式接口
 *
 */
@FunctionalInterface
public interface Functional<T, R> {

    R apply(T t) throws Exception;
}
