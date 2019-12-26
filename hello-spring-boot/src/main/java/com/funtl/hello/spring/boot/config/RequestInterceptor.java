package com.funtl.hello.spring.boot.config;

import com.alibaba.fastjson.JSON;
import com.funtl.hello.spring.boot.bean.MediaUserBean;
import com.funtl.hello.spring.boot.help.SessionLocalThread;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
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
		String userId = request.getHeader("username");
		if (StringUtils.isNotBlank(userId)) {
			SessionLocalThread.set(MediaUserBean.builder().username(userId).build());
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
		SessionLocalThread.removeLocalThread();
	}
}
