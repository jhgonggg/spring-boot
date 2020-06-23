package com.funtl.hello.spring.boot.service.impl;

import com.funtl.hello.spring.boot.entity.YbUser;
import com.funtl.hello.spring.boot.mapper.YbUserMapper;
import com.funtl.hello.spring.boot.plusRedis.redis.annotation.GetCache;
import com.funtl.hello.spring.boot.plusRedis.redis.enums.CacheParamEnum;
import com.funtl.hello.spring.boot.service.YbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author qy
 * @date 2020/6/23 11:30
 * @description
 */
@Service
public class YbUserServiceImpl implements YbUserService {
    @Autowired
    private YbUserMapper ybUserMapper;

    @GetCache(param = CacheParamEnum.USER, placeholders = {"#id"})
    @Override
    public YbUser getUser(Long id) {
        return Optional.ofNullable(ybUserMapper.selectByPrimaryKey(id)).orElseGet(YbUser::new);
    }
}
