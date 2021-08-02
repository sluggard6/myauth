package com.github.myauth.core;
//package com.github.auth.core;
//
//import java.io.IOException;
//import java.time.ZonedDateTime;
//import java.util.Collections;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import com.trashaus.taas.metadata.bean.AdminUser;
//import com.trashaus.taas.service.AdminUserService;
//import com.trashaus.taas.util.JwtTokenUtil;
//
//import io.fusionauth.jwt.domain.JWT;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RequiredArgsConstructor
//public class JwtAuthManager implements AuthManager {
//
//    private ThreadLocal<JwtToken> tokenLocal = new ThreadLocal<JwtToken>();
//    
//    /**
//     * 默认超时时间�?30分钟
//     */
//    private long timeOut = 30*60;
//    
//    @Override
//    public void setTimeOut(long duration, TimeUnit unit) {
//        timeOut = unit.toSeconds(duration);
//    }
//
//    private final AdminUserService adminUserService;
//
//    public void setToken(Token token) {
//        if (token instanceof JwtToken) {
//            tokenLocal.set((JwtToken) token);
//            return;
//        }
//        throw new IllegalArgumentException("JwtToken support only");
//    }
//
//    @Override
//    public Authentication<Long> getAuthentication() {
//        return getAuthenticationByToken(tokenLocal.get());
//    }
//
////	public List<Role> getRole() {
////		return getRolesByAuthentication(getAuthentication());
////	}
////	
////	public List<Permission> getRermission() {
////		return getRolesByAuthentication(getAuthentication());
////	}
//
//    public Authentication<Long> getAuthenticationByToken(Token token) {
//        try {
//			if (token == null)
//			    return null;
//			JwtToken jt = (JwtToken) token;
//			if(jt.getEncodeString() == null)
//				return null;
//			JWT jwt = JwtTokenUtil.decode(jt.getEncodeString());
//			if(checkTimeOut(jwt)) {
//			    updateToken(jwt);
//			    return adminUserService.getAdminUserById(jwt.getLong("userId"));
//			}
//			return null;
//		} catch (Exception e) {
//			throw new AuthFailedException(e);
//		}
//    }
//
//    private void updateToken(JWT jwt) {
//        try {
//            jwt.expiration = ZonedDateTime.now().plusSeconds(timeOut);
//            tokenLocal.set(new JwtToken(JwtTokenUtil.encode(jwt)));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//            new AuthFailedException("update token filed cause " + e.getMessage(), e);
//        }
//    }
//
//    private boolean checkTimeOut(JWT jwt) {
//        ZonedDateTime exp = (ZonedDateTime) jwt.getObject("exp");
//        return exp.isAfter(ZonedDateTime.now());
//    }
//
//    public boolean checkPermission(String[] permissionNames) {
//        Authentication<Long> authentication = getAuthentication();
//        //如果没有登陆信息，认证失�?
//        if (authentication == null){return false;}
//        //如果没有确切的权限，即登陆即�?
//        if(permissionNames.length == 0) {return true;}
//        for (String name : permissionNames) {
//            if (adminUserService.checkPermission(name, authentication.identify())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public void login(Authentication<?> authentication) {
//        try {
//            if(authentication instanceof AdminUser) {
//                JWT jwt = new JWT().setIssuer("www.trashaus.com")
//                    .setIssuedAt(ZonedDateTime.now())
//                    .setSubject(String.valueOf(authentication.identify()))
//                    .setExpiration(ZonedDateTime.now().plusSeconds(timeOut));
//                jwt.addClaim("userId", authentication.identify());
//                tokenLocal.set(new JwtToken(JwtTokenUtil.encode(jwt)));
//            }
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//            new AuthFailedException("create token filed cause " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public String getTokenString() {
//        JwtToken token = tokenLocal.get();
//        if (token != null) {
//            return token.getEncodeString();
//        }
//        return "";
//    }
//
//	@Override
//	public List<? extends Permission> getPermissions() {
//        Authentication<Long> authentication = getAuthentication();
//        //如果没有登陆信息，认证失�?
//        if (authentication == null){return Collections.emptyList();}
//		return adminUserService.getAllPermissionByUserId(authentication.identify());
//	}
//
//
////	public List<Role> getRolesByAuthentication(Authentication a) {
////		return null;
////	}
////	
////	public List<Permission> getPermissionByRole()
//
//}
