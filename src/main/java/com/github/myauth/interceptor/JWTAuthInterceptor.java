package com.github.myauth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.github.myauth.core.AuthManager;
import com.github.myauth.core.JwtToken;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTAuthInterceptor implements HandlerInterceptor {
	
	@Autowired
	private final AuthManager authManager;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        String bearer = request.getHeader("Authorization");
        if(bearer != null) {
            if(bearer.startsWith("Bearer")) {
                bearer = bearer.substring("Bearer".length()).trim();
            }
            authManager.setToken(new JwtToken(bearer));
        }
        return true;
	}

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        response.addHeader("Authorization", authManager.getTokenString());
        response.setHeader("Authorization", authManager.getTokenString());
    }
}
