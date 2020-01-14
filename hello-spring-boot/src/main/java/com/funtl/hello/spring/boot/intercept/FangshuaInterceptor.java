package com.funtl.hello.spring.boot.intercept;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.funtl.hello.spring.boot.annotation.AccessLimit;
import com.funtl.hello.spring.boot.help.TokenThreadLocal;
import com.funtl.hello.spring.boot.redis.RedisKey;
import com.funtl.hello.spring.boot.redis.RedisManager;
import com.funtl.hello.spring.boot.redis.impl.RedisImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author qy
 * @date 2019/12/3 14:49
 * @description 防止同一个用户 N 秒内一直访问同一个接口
 */
@Component
@Slf4j
@Order(1)
public class FangshuaInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RedisImpl redis;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("第二执行");

        //判断请求是否属于方法的请求
        if (handler instanceof HandlerMethod) {

            HandlerMethod hm = (HandlerMethod) handler;

            //获取方法中的注解,看是否有该注解
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if (accessLimit == null) {
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean login = accessLimit.needLogin();
            String key = request.getRequestURI();
            String userId = TokenThreadLocal.getUserId();
            //如果需要登录
            if (login) {
                //获取登录的session进行判断
                String sessionId = RedisManager.get(RedisKey.USER_SESSION_KEY);
                String nowSessionId = request.getSession().getId();
                if (StrUtil.equals(nowSessionId, sessionId)) {
                    if (StringUtils.isNotBlank(userId)) {
                        key += "" + userId;  //项目中是动态获取的userId
                    } else {
                        return false;
                    }
                } else {
                    throw new RuntimeException("账号已在别处登录");
                }
            }
/////////////////////////////////////////////////////////////////////
            //  不能这么写
//            //从redis中获取用户访问的次数
////            AccessKey ak = AccessKey.withExpire(seconds);
//            Integer count = (Integer) redis.get(key);   // 可能多个线程同时访问到这里 拿到了 count
//            if (count == null) {
//                //第一次访问
//                redis.set(key, 1, seconds, TimeUnit.SECONDS);
//            } else if (count < maxCount) {
//                //加1
//                redis.increment(key, 1);
//            } else {
//                //超出访问次数
//                render(response);
//                return false;
//            }
////////////////////////////////////////////////////////////////////
            try {
                Long visitTimes = redis.increment(key, 1L);
                //代表是第一次访问，设置超时时间
                if (visitTimes == 1) {
                    redis.expire(key, seconds, TimeUnit.SECONDS);
                    return false;
                    //访问次数超过最大次数
                } else {
                    return visitTimes > maxCount;
                }
            } catch (Exception e) {
                log.error("redis 异常", e);
            } finally {
                redis.expire(key, seconds, TimeUnit.SECONDS);
            }
        }

        return true;
    }

    private void render(HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String str = JSON.toJSONString("超出次数");
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }

}
