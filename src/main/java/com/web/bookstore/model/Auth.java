package com.web.bookstore.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * @brief 身份认证的类
 */
@Data
@Entity
@Table(name = "auth")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "password")
    private String password;

    public Auth() {
    }

    public Auth(User user) {

        this.user = user;
    }

    public Auth(User user, String password) {

        this.user = user;
        this.password = password;
    }

}