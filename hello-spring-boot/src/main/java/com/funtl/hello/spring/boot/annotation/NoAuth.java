package com.funtl.hello.spring.boot.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoAuth {
    String[] path() default {};
}
