package com.funtl.hello.spring.boot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.SortedMap;

/**
 * @author qy
 * @date 2020/6/18 11:02
 * @description
 */
@Slf4j
public class ObjectSignUtils {

    private static final String SALT = "c68c5a30-a4c3-4751-8228-8e22add578ec";


    /**
     * 把对象按照 key=value方式组装成字符串
     * 然后通过md5+sha的加密方式生成签名 返回 sha256
     *
     * @param bean
     * @return
     */
    public static String generateSign(Serializable bean) {
        SortedMap<String, Object> sortedMap = JSON.parseObject(JSON.toJSONString(bean), new TypeReference<SortedMap<String, Object>>() {
        });
        StringBuffer buffer = new StringBuffer();
        sortedMap.entrySet().stream().filter(e -> Objects.nonNull(e.getValue())).forEach(e -> buffer.append(e.getKey()).append(e.getValue()));
        return DigestUtils.sha256Hex(DigestUtils.md5Hex(DigestUtils.sha1Hex(Base64.encodeBase64(buffer.append(SALT).toString().getBytes(StandardCharsets.UTF_8)))));
    }


    /**
     * 把java bean对象生成 base64字符串
     *
     * @param bean
     * @return
     */
    public static String generateBase64(Object bean) {
        return Base64.encodeBase64URLSafeString(JSON.toJSONBytes(bean));
    }


    /**
     * 把base64还原成对象
     *
     * @param base64
     * @param <T>
     * @return
     */
    public static <T> T recoveryBase64ToObject(String base64, Class<T> clazz) {
        try {
            return JSON.parseObject(Base64.decodeBase64(base64), clazz);
        } catch (Exception e) {
            log.error("恢复出错", e);
            return null;
        }
    }
}
