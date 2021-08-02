package com.github.myauth.core;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.github.myauth.core.annotation.AuthRequired;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@RequiredArgsConstructor
public class AuthAspect {
	
	private final AuthManager authManager;
	
	@Pointcut("@annotation(com.xinhua.crm.auth.core.annotation.AuthRequired)")
	public void authPoinCut(){}
	
	@Around("authPoinCut()&&(@annotation(authRequired))")
	public Object doAround(ProceedingJoinPoint joinPoint, AuthRequired authRequired) throws Throwable {
		if (log.isDebugEnabled()) {
			log.debug(joinPoint.getKind());
		}
		if(!authManager.checkPermission(authRequired.permission())) {
			throw new AuthFailedException(String.format("auth failed by permission : %s", Arrays.deepToString(authRequired.permission())));
		}else {
			return joinPoint.proceed();
		}
	}
	
}
