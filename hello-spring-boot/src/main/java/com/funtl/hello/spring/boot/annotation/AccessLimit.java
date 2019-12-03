package com.funtl.hello.spring.boot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author qy
 * @date 2019/12/3 14:37
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AccessLimit {
      // 最大访问数 默认 10
      int maxCount() default 10;
      // 多少秒内可以访问多少次 默认 1秒
      int seconds() default 1;
      // 是否需要登录
      boolean needLogin() default true;

}
