package com.web.bookstore.dto;

import lombok.Data;

import com.web.bookstore.model.User;

import jakarta.persistence.criteria.CriteriaBuilder.In;

@Data
public class UserDTO {
    private Integer id;
    private String name;
    private String avatar;
    private String description;
    private String account;
    private String email;
    private Integer role;
    private Integer status;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.avatar = user.getAvatar();
        this.description = user.getDescription();
        this.account = user.getAccount();
        this.role = user.getRole();
        this.email = user.getEmail();
        this.status = user.getStatus();
    }
}
