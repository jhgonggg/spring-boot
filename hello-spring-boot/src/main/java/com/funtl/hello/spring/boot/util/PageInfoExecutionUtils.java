package com.funtl.hello.spring.boot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author qy
 * @date 2020/6/18 10:18
 * @description 分页映射转换类
 */
public class PageInfoExecutionUtils {

//      PageInfo<UserVO> pageInfo = PageInfoExecutionUtils.map(UserInfo, (ref) -> ref.convertExt(UserVO.class));

    public static <U, T> PageInfo<U> map(PageInfo<T> pageInfo, Function<? super T, ? extends U> converter) {
        PageInfo<U> info = JSON.parseObject(JSON.toJSONString(pageInfo), new TypeReference<PageInfo<U>>() {
        });
        info.setList(pageInfo.getList().stream().map(converter).collect(Collectors.toList()));
        return info;
    }

//    public <T> T convertExt(Class<T> clazz) {
//        String json = JSON.toJSONString(this);
//        T t = JSON.parseObject(json, clazz);
//        return t;
//    }

}
