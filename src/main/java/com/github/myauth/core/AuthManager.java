package com.github.myauth.core;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface AuthManager<T> {
    
    void setTimeOut(long duration, TimeUnit unit);

    void setToken(Token token);

    boolean checkPermission(String[] permission);

    Token login(Authentication<T> authentication);
    
	void logout();

    String getTokenString();

	List<? extends Permission> getPermissions();

	Authentication<T> getAuthentication();

	void delay();


}
