package com.funtl.hello.spring.boot.config;

import com.alibaba.fastjson.JSON;
import com.funtl.hello.spring.boot.annotation.Pm;
import com.funtl.hello.spring.boot.bean.LoginToken;
import com.funtl.hello.spring.boot.help.TokenThreadLocal;
import com.funtl.hello.spring.boot.util.CommonHelper;
import com.funtl.hello.spring.boot.util.DesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
//执行顺序
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
        log.info("url-->{}", url);  //  /list
        if (RequestMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            return super.preHandle(request, response, handler);
        }
        String token = request.getHeader("token");
        if (StringUtils.isNotBlank(token)) {
            // 解密
            LoginToken loginToken;
            try {
                loginToken = JSON.parseObject(DesUtil.decrypt(token), LoginToken.class);
            } catch (IOException e) {
                log.error("请求拦截器解析token异常, e-->{}, token-->{}", e, token);
                throw new RuntimeException("用户未登录或 token 已失效");
            }
            //  设置 ThreadLocal
            TokenThreadLocal.setLoginToken(loginToken);
            log.info("url-->{}, method-->{}, param-->{}, loginToken-->{}, userId-->{}", url, request.getMethod(), JSON.toJSONString(request.getParameterMap()), loginToken, loginToken.getUserId());
            HandlerMethod method;
            if (handler instanceof HandlerMethod) {
                method = (HandlerMethod) handler;
            } else {
                return super.preHandle(request, response, handler);
            }
            Pm pm = method.getMethodAnnotation(Pm.class);
            if (Objects.nonNull(pm)) {
                // 请求路径  request.getParameterMap() --->  Map<String, String[]>  获取请求参数
                String resourcesUri = CommonHelper.assembleResourcesUri(url, JSON.parseObject(JSON.toJSONString(request.getParameterMap())));
                boolean bool = this.havePm(loginToken.getUserId(), resourcesUri);
                if (!bool) {
                    throw new RuntimeException("没有权限");
                }
            }
        }
        return super.preHandle(request, response, handler);
    }

//    private static final AntPathMatcher MATCHER = new AntPathMatcher();

    private boolean havePm(String userId, String resourcesUri) {
//        List<UserRole> userRoles = userRoleService.selectRoles(userId);
//
//        // 没分配有角色的用户处理方案待定
//        if (IterUtil.isEmpty(userRoles)) {
//            log.warn("用户未分配角色, 禁止访问任何资源-->{}", userId);
//            return false;
//        }
//        List<ResourcesDTO> resources = roleResourcesService.selectResources(
//                userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList()));
//        return resources.stream().anyMatch(e -> StringUtils.equalsIgnoreCase(e.getUri(), resourcesUri)
//                || (StringUtils.isNoneBlank(e.getUri()) && MATCHER.match(e.getUri(), resourcesUri))
//                || Optional.ofNullable(e.getChild()).orElse(new ArrayList<>()).stream().map(ResourcesDTO::getUri)
//                .filter(StringUtils::isNoneBlank)
//                .anyMatch(an -> StringUtils.equalsIgnoreCase(an, resourcesUri) || MATCHER.match(an, resourcesUri)));
        return true;
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
