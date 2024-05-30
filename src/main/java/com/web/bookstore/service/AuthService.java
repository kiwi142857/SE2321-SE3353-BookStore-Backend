package com.web.bookstore.service;

import com.web.bookstore.dto.LoginRequestDTO;
import com.web.bookstore.dto.RegisterRequestDTO;
import javax.naming.AuthenticationException;

import java.util.Optional;

import com.web.bookstore.model.User;
import com.web.bookstore.model.Auth;

public interface AuthService {
    /**
     * @brief 用户登录函数
     * @param dto 登录请求
     * @return 若登录成功，返回Auth
     * @throws AuthenticationException 登录异常
     */
    Auth login(LoginRequestDTO dto) throws AuthenticationException;

    /**
     * @brief 用户注册函数
     * @param dto 注册请求
     */
    void register(RegisterRequestDTO dto);

    /**
     * @brief 根据用户查找Auth
     * @param user 用户
     * @return 若找到，返回Auth
     */
    Optional<Auth> findByUser(User user);
}
