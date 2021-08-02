package com.github.myauth.core;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SessionAuthManager implements HttpAuthManager {

    @Override
    public void setTimeOut(long duration, TimeUnit unit) {
        throw new UnsupportedOperationException("config in web.xml");
    }

    @Override
    public void setToken(Token token) {

    }

    @Override
    public boolean checkPermission(String[] permission) {
        return false;
    }

    @Override
    public Token login(Authentication authentication) {
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public String getTokenString() {
        return null;
    }

    @Override
    public List<? extends Permission> getPermissions() {
        return null;
    }

    @Override
    public Authentication getAuthentication() {
        return null;
    }

    @Override
    public void delay() {

    }

    @Override
    public void login(HttpSession session, Authentication authentication) {

    }

    @Override
    public Authentication getAuthentication(HttpSession session) {
        return null;
    }
}
