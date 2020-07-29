//package com.funtl.hello.spring.boot.config;
//
//import cn.hutool.core.date.SystemClock;
//import com.alibaba.fastjson.JSON;
//import com.funtl.hello.spring.boot.annotation.NoAuth;
//import com.funtl.hello.spring.boot.bean.LoginToken;
//import com.funtl.hello.spring.boot.constant.SysConst;
//import com.funtl.hello.spring.boot.redis.RedisKey;
//import com.funtl.hello.spring.boot.response.MsgCode;
//import com.funtl.hello.spring.boot.util.DesUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
//import java.util.Objects;
//import java.util.concurrent.TimeUnit;
//
//
//@Component
//@Aspect
//@Slf4j
//public class Aop {
//
//    @Autowired
//    private RedisTemplate<String,Object> redisTemplate;
//
//    /**
//     * service层方法记录，系统稳定后会去掉
//     */
//    @Around("execution(* com.funtl.hello.spring.boot.service..*.*(..))")
//    public Object logServiceAop(ProceedingJoinPoint invocation) throws Throwable {
//        long start = SystemClock.now();
//        try {
//            return invocation.proceed();
//        } catch (Exception ex) {
//            log.error("logAop异常-->{}", ex.getMessage());
//            throw ex;
//        } finally {
//            log.info("service类:{} 方法:{} 耗时:{}ms", invocation.getTarget().getClass().getName(), invocation.getSignature().getName(), SystemClock.now() - start);
//        }
//    }
//
//    @Pointcut("execution(* com.funtl.hello.spring.boot.controller..*.*(..))")
//    public void webAop(){}
//
//    /**
//     * 方法执行前
//     */
//    @Before(value = "webAop()")
//    public void before(JoinPoint joinPoint) throws NoSuchMethodException {
//        Annotation annotation = null;
//        Class clazz = joinPoint.getTarget().getClass();
//        String methodName = joinPoint.getSignature().getName();
//        String name = joinPoint.getSignature().getDeclaringTypeName().concat(SysConst.DOT).concat(joinPoint.getSignature().getName());
//        if (!SysConst.ADMIN_EXCLUDES.contains(name)) {
//            Class[] parameterTypes = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
//            Method method = clazz.getMethod(methodName,parameterTypes);
//            annotation = method.getAnnotation(NoAuth.class);
//        }
//
//        if (Objects.isNull(annotation) && !SysConst.ADMIN_EXCLUDES.contains(name)) {
//            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
//            //开始获取用户信息
//            String requestUri = request.getRequestURI();
//            String token = request.getHeader(SysConst.TOKEN);
//            String userId = request.getParameter("userId");
//            log.info("userId:{}, url:{}，token信息:{}", userId, requestUri, token);
//            if (StringUtils.endsWithAny(requestUri, SysConst.ADMIN_END_WITHS)) {
//                return;
//            }
//            if (StringUtils.isEmpty(token)) {
//                throw new SouthcnException(MsgCode.NO_LOGIN);
//            }
//            LoginToken loginToken;
//            try {
//                loginToken = JSON.parseObject(DesUtil.decrypt(token), LoginToken.class);
//            } catch (Exception e) {
//                log.error("token解析异常-->{}, token-->{}, userId-->{}", e, token, userId);
//                throw new SouthcnException(MsgCode.NO_LOGIN);
//            }
//            Object objRedis = redisTemplate.opsForValue().get(RedisKey.USER_LOGIN_KEY + loginToken.getUuid());
//            String redisToken = Objects.isNull(objRedis) ? null : objRedis.toString();
//            if (StringUtils.isAnyBlank(redisToken, token)) {
//                log.error("用户未登录或已失效, userId-->{}, redisToken-->{}, token-->{}", userId, JSON.toJSONString(loginToken), token);
//                throw new SouthcnException(MsgCode.NO_LOGIN, "用户未登录或已失效");
//            }
//            if (!StringUtils.equals(token, redisToken)) {
//                log.error("用户登录已失效, userId-->{}, redisToken-->{}, token-->{}", userId, JSON.toJSONString(loginToken), token);
//                throw new SouthcnException(MsgCode.NO_VALID, "用户未登录或已失效");
//            }
//            redisTemplate.opsForValue().set(RedisKey.USER_LOGIN_KEY + loginToken.getUuid(), Objects.requireNonNull(redisToken), 360, TimeUnit.MINUTES);
//        }
//    }
//}