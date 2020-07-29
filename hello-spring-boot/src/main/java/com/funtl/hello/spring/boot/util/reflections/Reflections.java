package com.funtl.hello.spring.boot.util.reflections;

import java.beans.Introspector;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author qy
 * @date 2020/7/29 16:41
 * @description
 */
public class Reflections {
    private static final Pattern GET_PATTERN = Pattern.compile("^get[A-Z].*");
    private static final Pattern IS_PATTERN = Pattern.compile("^is[A-Z].*");

    private Reflections() {
    }

    public static String fnToFieldName(ConverterFn fn) {
        try {
            Method method = fn.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(fn);
            String getter = serializedLambda.getImplMethodName();
            if (GET_PATTERN.matcher(getter).matches()) {
                getter = getter.substring(3);
            } else if (IS_PATTERN.matcher(getter).matches()) {
                getter = getter.substring(2);
            }
            return Introspector.decapitalize(getter);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
