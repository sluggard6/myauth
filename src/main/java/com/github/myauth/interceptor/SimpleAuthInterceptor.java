package com.github.myauth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.github.myauth.core.AuthManager;
import com.github.myauth.token.SimpleToken;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleAuthInterceptor implements HandlerInterceptor {
	
	private final AuthManager authManager;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getHeader("authorization");
		if(StringUtils.hasText(token)) {
			authManager.setToken(new SimpleToken(token));
		}
 		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		response.addHeader("authorization", authManager.getTokenString());
		authManager.delay();
	}

	

}
