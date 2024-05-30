package com.web.bookstore.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.method.HandlerMethod;
import jakarta.servlet.http.Cookie;

@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Object object) throws Exception {

        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        String token = null;
        String JSSESSIONID = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
                if ("JSESSIONID".equals(cookie.getName())) {
                    JSSESSIONID = cookie.getValue();
                }
            }
        }

        if ((token == null || token.trim().isEmpty()) && (JSSESSIONID == null || JSSESSIONID.trim().isEmpty())) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }
}