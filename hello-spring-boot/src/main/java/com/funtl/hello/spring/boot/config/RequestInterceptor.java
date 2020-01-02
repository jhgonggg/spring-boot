package com.funtl.hello.spring.boot.config;

import com.alibaba.fastjson.JSON;
import com.funtl.hello.spring.boot.bean.LoginToken;
import com.funtl.hello.spring.boot.help.TokenThreadLocal;
import com.funtl.hello.spring.boot.util.DesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class RequestInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		String url = urlPathHelper.getLookupPathForRequest(request);
		if (RequestMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
			return super.preHandle(request, response, handler);
		}
		String token = request.getHeader("token");
		if (StringUtils.isNotBlank(token)) {
			LoginToken loginToken = JSON.parseObject(DesUtil.decrypt(token), LoginToken.class);
			TokenThreadLocal.setLoginToken(loginToken);
			log.info("url-->{}, method-->{}, parmer-->{}, loginToken-->{}", url, request.getMethod(), JSON.toJSONString(request.getParameterMap()), loginToken);
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		TokenThreadLocal.delLoginToken();
		super.afterCompletion(request, response, handler, ex);
	}
}
