package com.github.myauth.core;

public abstract class AbstractAuthManager<T> implements AuthManager<T> {

	protected ThreadLocal<Token> tokenLocal = new ThreadLocal<>();

	@Override
	public void setToken(Token token) {
		tokenLocal.set(token);
	}
	
}
