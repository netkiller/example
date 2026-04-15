package cn.netkiller.welcome.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import cn.netkiller.welcome.component.RestfulApiCounter;

public class PrometheusInterceptor implements HandlerInterceptor {

	@Autowired
	private RestfulApiCounter restfulApiCounter;

	public PrometheusInterceptor() {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

		restfulApiCounter.increment();
	}

}