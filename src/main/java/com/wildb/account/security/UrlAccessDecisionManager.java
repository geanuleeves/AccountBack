package com.wildb.account.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;



/**
 * Created by yangyibo on 17/1/19.
 */
@Service
public class UrlAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        String url, method;
        if ("anonymousUser".equals(authentication.getPrincipal())
                || matchers("/static/img/**", request)
                || matchers("/static/js/**", request)
                || matchers("/static/assets/**",request)
                || matchers("/static/css/**", request)
                || matchers("/static/fonts/**", request)
//                || matchers("/index.html", request)
                || matchers("/favicon.ico", request)
//                || matchers("/", request)
                || matchers("/login",request)
               ) {
            return;
        } else {
            if(matchers("/",request)){ //用户登录后如果是访问首页，则通过
                return;
            }
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (ga instanceof UrlGrantedAuthority) {
                    UrlGrantedAuthority urlGrantedAuthority = (UrlGrantedAuthority) ga;
                    url = urlGrantedAuthority.getPermissionUrl();
                    method = urlGrantedAuthority.getMethod();
                    if (matchers(url, request)) {
                        if (method.equals(request.getMethod()) || "ALL".equals(method)) {
                            return;
                        }
                    }
                }
            }
        }
        throw new AccessDeniedException("no right");
    }


    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }
//    ROLE_ANONYMOUS

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }


    private boolean matchers(String url, HttpServletRequest request) {
        AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
        if (matcher.matches(request)) {
            return true;
        }
        return false;
    }
}
