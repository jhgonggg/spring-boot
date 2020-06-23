package com.funtl.hello.spring.boot.service;

import com.funtl.hello.spring.boot.entity.YbUser;

/**
 * @author qy
 * @date 2020/6/23 11:29
 * @description
 */
public interface YbUserService {

    YbUser getUser(Long id);
}
