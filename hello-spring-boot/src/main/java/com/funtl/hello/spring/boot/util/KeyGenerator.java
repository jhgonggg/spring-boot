package com.funtl.hello.spring.boot.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class KeyGenerator {

    /**
     * 拼接Redis Key
     *
     * @param keyPrefix
     * @param keys
     * @return
     */
    @Deprecated
    public static String generator(String keyPrefix, String... keys) {
        StringBuilder generatorKey = new StringBuilder(keyPrefix);
        Arrays.stream(keys).forEach(key -> generatorKey.append(key).append("."));
        return generatorKey.deleteCharAt(generatorKey.length() - 1).toString();
    }

    /**
     * 拼接redis key(自动判断最后是否有".")
     *
     * @param keyPrefix
     * @param params
     * @return
     */
    public static String generate(String keyPrefix, String... params) {
        StringBuilder generatorKey = new StringBuilder(keyPrefix);
        Arrays.stream(params).forEach(param -> {
            if (!StringUtils.endsWith(generatorKey, ".")) {
                generatorKey.append(".");
            }
            generatorKey.append(param);
        });
        return generatorKey.toString();
    }

}
