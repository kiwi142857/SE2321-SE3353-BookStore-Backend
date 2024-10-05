package com.web.bookstore.service;

import com.web.bookstore.model.User;

import com.web.bookstore.dto.UpdateUserInfoRequestDTO;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.dto.GetUserListOk;
import javax.security.sasl.AuthenticationException;

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
     * @param user                     用户
     */
    ResponseDTO updateUserInfo(
            UpdateUserInfoRequestDTO updateUserInfoRequestDTO,
            User user) throws AuthenticationException;

    ResponseDTO changePassword(User user, String oldPassword, String newPassword);

    GetUserListOk getUserList(Integer pageSize, Integer pageIndex, String keyWord, Integer id);

    ResponseDTO banUser(User targetUser);

    ResponseDTO unbanUser(User targetUser);

    GetUserListOk getSalesRankList(Integer pageIndex, Integer pageSize, String startTime, String endTime);

    void save(User user);
}
