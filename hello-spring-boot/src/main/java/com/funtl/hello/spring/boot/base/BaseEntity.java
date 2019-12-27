package com.funtl.hello.spring.boot.base;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qy
 * @date 2019/12/27 16:26
 * @description
 */
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -8085972019022784632L;

    public <T> T convertExt(Class<T> clazz) {
        String json = JSON.toJSONString(this);
        T t = JSON.parseObject(json, clazz);
        return t;
    }
}
