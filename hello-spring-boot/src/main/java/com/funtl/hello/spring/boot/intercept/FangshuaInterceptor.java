package com.funtl.hello.spring.boot.intercept;

import com.alibaba.fastjson.JSON;
import com.funtl.hello.spring.boot.annotation.AccessLimit;
import com.funtl.hello.spring.boot.redis.impl.RedisImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @description
 */
@Component
@Slf4j
public class FangshuaInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RedisImpl redis;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
            String userId = request.getParameter("userId");
            //如果需要登录
            if (login) {
                //获取登录的session进行判断
                //.....
//                if ("未登录"){
//                    return false;
//                }
                key += "" + userId;  //项目中是动态获取的userId
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
                    redis.expire(key, 1, TimeUnit.SECONDS);
                    return false;
                    //访问次数超过最大次数
                } else {
                    return visitTimes > 20;
                }
            } catch (Exception e) {
                log.error("",e);
            }finally {
                redis.expire(key,1,TimeUnit.SECONDS);
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
