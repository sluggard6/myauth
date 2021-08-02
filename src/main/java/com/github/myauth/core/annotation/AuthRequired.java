package com.github.myauth.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.myauth.core.AuthType;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
public @interface AuthRequired {
	
	String[] permission() default {};
	
	AuthType type() default AuthType.SESSION;
	
}
