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
     * @return 若登录成功，返回更新后的token
     * @throws AuthenticationException 登录异常
     */
    String login(LoginRequestDTO dto) throws AuthenticationException;

    /**
     * @brief 用户注册函数
     * @param dto 注册请求
     */
    void register(RegisterRequestDTO dto);

    /**
     * @brief 根据token获取用户
     * @param token 用户token
     * @return 用户
     */
    User getUserByToken(String token);

    /**
     * @brief Jaccount登录函数
     * @param code Jaccount返回的code
     * @return 若登录成功，返回更新后的token
     * @throws AuthenticationException 登录异常
     */
    String jaccountLogin(String code) throws AuthenticationException;

    /**
     * @brief 根据token获取Auth
     * @param token 用户token
     * @return Auth
     */
    Optional<Auth> getAuthByToken(String token);
}
