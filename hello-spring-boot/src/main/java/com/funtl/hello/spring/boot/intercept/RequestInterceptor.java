package com.funtl.hello.spring.boot.intercept;

import com.alibaba.fastjson.JSON;
import com.funtl.hello.spring.boot.bean.LoginToken;
import com.funtl.hello.spring.boot.help.TokenThreadLocal;
import com.funtl.hello.spring.boot.util.DesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Order(0)
public class RequestInterceptor extends HandlerInterceptorAdapter {
    /**
     * Controller方法处理之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("第一执行");
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        String url = urlPathHelper.getLookupPathForRequest(request);
        if (RequestMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            return super.preHandle(request, response, handler);
        }
        String token = request.getHeader("token");
        if (StringUtils.isNotBlank(token)) {
            // 解密
            LoginToken loginToken = JSON.parseObject(DesUtil.decrypt(token), LoginToken.class);
            //  设置 ThreadLocal
            TokenThreadLocal.setLoginToken(loginToken);
            log.info("url-->{}, method-->{}, param-->{}, loginToken-->{}", url, request.getMethod(), JSON.toJSONString(request.getParameterMap()), loginToken);
        }
        return super.preHandle(request, response, handler);
    }

    /**
     * Controller方法处理完之后 、试图渲染之前 、可以对ModelAndView进行操作
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 视图的渲染之后、多用于清除资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TokenThreadLocal.delLoginToken();  // 手动释放内存，从而避免内存泄漏

        super.afterCompletion(request, response, handler, ex);
    }
}
