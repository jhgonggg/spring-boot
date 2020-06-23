package com.funtl.hello.spring.boot.annotation;

import com.funtl.hello.spring.boot.help.ContentValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ContentValidator.class)
public @interface ContentValidate {

    String message() default "内容不能超过16000字！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
