package com.funtl.hello.spring.boot.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
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
		log.info("url-->{}, method-->{}, parmer-->{}", url, request.getMethod(), JSON.toJSONString(request.getParameterMap()));
		return super.preHandle(request, response, handler);
	}
}
