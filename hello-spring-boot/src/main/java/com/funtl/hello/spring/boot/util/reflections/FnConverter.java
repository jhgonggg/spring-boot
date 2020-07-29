package com.funtl.hello.spring.boot.util.reflections;

/**
 * @author qy
 * @date 2020/7/29 16:39
 * @description
 */
public class FnConverter<T> {

    public String fnToFieldName(ConverterFn<T> fn) {
        return Reflections.fnToFieldName(fn);
    }

    public static <A> FnConverter<A> of(Class<A> clazz) {
        return new FnConverter<A>();
    }
}
