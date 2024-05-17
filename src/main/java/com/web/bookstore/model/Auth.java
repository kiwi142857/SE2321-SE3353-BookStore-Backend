package com.web.bookstore.model;

import jakarta.persistence.*;
import lombok.Data;

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

    @Column(name = "token")
    private String token;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "password")
    private String password;

    public Auth() {

    }

    public Auth(User user) {
        updateToken();
        this.user = user;
    }

    public Auth(User user, String password) {
        updateToken();
        this.user = user;
        this.password = password;
    }

    public void updateToken() {
        this.token = UUID.randomUUID().toString();
    }
}
