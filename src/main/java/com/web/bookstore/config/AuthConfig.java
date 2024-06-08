package com.web.bookstore.config;

import com.web.bookstore.interceptor.AuthHandlerInterceptor;
import com.web.bookstore.interceptor.AdminHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthConfig implements WebMvcConfigurer {

    @Autowired
    AuthHandlerInterceptor authHandlerInterceptor;

    @Autowired
    AdminHandlerInterceptor adminHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authHandlerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/auth/*");
        registry.addInterceptor(adminHandlerInterceptor)
                .addPathPatterns("/api/administrator/*");
    }

}
