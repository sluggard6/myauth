package com.github.myauth.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.github.myauth.token.SimpleToken;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleAuthManager<T> extends AbstractAuthManager<T> implements AuthManager<T> {

    private Map<Token, TokenInfo<T>> tokenMap = new HashMap<>();

    private long timeOut;

    @Override
    public void setTimeOut(long duration, TimeUnit unit) {
        timeOut = unit.toMillis(duration);
    }

    @Override
    public void setToken(Token token) {
        tokenLocal.set(token);
    }

    @Override
    public boolean checkPermission(String[] permission) {
        if (!tokenMap.containsKey(getToken())) return false;
        System.out.println(tokenMap.get(tokenLocal.get()).getOutTime() + ":" + System.currentTimeMillis());
        return tokenMap.get(tokenLocal.get()).getOutTime() > System.currentTimeMillis();
    }

    @Override
    public Token login(Authentication<T> authentication) {
        Token token = new SimpleToken(UUID.randomUUID().toString().replace("-", ""));
        tokenMap.put(token, new TokenInfo<T>(authentication, System.currentTimeMillis()));
        setToken(token);
        if (log.isDebugEnabled()) {
            log.debug(token.toString());
        }
        return token;
    }

    @Override
    public String getTokenString() {
        return String.valueOf(tokenLocal.get());
    }

    private Token getToken() {
        return tokenLocal.get();
    }

    @Override
    public List<? extends Permission> getPermissions() {
        return null;
    }

    @Override
    public Authentication<T> getAuthentication() {
        return tokenMap.get(getToken()).getAuthentication();
    }

    @Override
    public void delay() {
        if (tokenMap.containsKey(tokenLocal.get())) {
            tokenMap.get(tokenLocal.get()).setOutTime(System.currentTimeMillis() + timeOut);
        }
    }

    @Override
    public void logout() {
        Token token = tokenLocal.get();
        tokenMap.remove(token);
        tokenLocal.remove();
    }

}

@Data
@AllArgsConstructor
class TokenInfo<T> {
    Authentication<T> authentication;
    Long outTime;
}
