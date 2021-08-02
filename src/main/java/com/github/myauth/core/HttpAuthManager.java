package com.github.myauth.core;

import javax.servlet.http.HttpSession;

public interface HttpAuthManager extends AuthManager{

    void login(HttpSession session, Authentication authentication);

    Authentication getAuthentication(HttpSession session);

}
