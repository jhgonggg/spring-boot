package com.funtl.hello.spring.boot.help;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;


/**
 * Description: map接收的函数，用于从业务代码中抽离try catch语句块
 */
@Slf4j
public class FunctionWrap {

    public static <T, R> Function<T, R> wrap(Functional<T, R> functional) {
        return t -> {
            try {
                return functional.apply(t);
            } catch (Exception e) {
                log.error("FunctionWrap exception-->{}, t-->{}", e, t);
//                 throw new RuntimeException(e);
            }
            return null;
        };
    }
}
