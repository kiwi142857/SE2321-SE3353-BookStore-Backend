package com.web.bookstore.service;

import com.web.bookstore.model.User;
import com.web.bookstore.dto.UpdateUserInfoRequestDTO;
import com.web.bookstore.dto.ResponseDTO;
import javax.security.sasl.AuthenticationException;

import org.apache.catalina.connector.Response;

public interface UserService {

    /**
     * @brief 根据用户id查找用户
     * @param id 用户id
     * @return 用户
     */
    User findUserById(Integer id);

    /**
     * @brief 更新用户信息
     * @param id                       用户id
     * @param updateUserInfoRequestDTO 更新用户信息请求
     * @param token                    用户token
     */
    void updateUserInfo(
            Integer id,
            UpdateUserInfoRequestDTO updateUserInfoRequestDTO,
            String token) throws AuthenticationException;

    ResponseDTO changePassword(String token, String oldPassword, String newPassword);
}
