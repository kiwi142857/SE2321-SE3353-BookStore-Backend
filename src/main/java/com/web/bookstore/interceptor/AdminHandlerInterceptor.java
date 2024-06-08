package com.web.bookstore.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.web.bookstore.model.User;
import com.web.bookstore.util.SessionUtils;

import org.springframework.web.method.HandlerMethod;
import jakarta.servlet.http.Cookie;

@Component
public class AdminHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Object object) throws Exception {

        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        String JSSESSIONID = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {

                if ("JSESSIONID".equals(cookie.getName())) {
                    JSSESSIONID = cookie.getValue();
                }
            }
        }

        User user = SessionUtils.getUser();

        if (user == null) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        if (user.getRole() != 1) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        if (JSSESSIONID == null || JSSESSIONID.trim().isEmpty()) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }
}