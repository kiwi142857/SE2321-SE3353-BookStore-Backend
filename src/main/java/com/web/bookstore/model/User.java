package com.web.bookstore.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.web.bookstore.dto.RegisterRequestDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.web.bookstore.dto.JaccountUserDTO;

@Data
@Entity
@Table(name = "user")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "account")
    private String account;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<BookRate> bookRates = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Auth auth;

    public User(RegisterRequestDTO dto) {
        this.name = dto.getUsername();
        this.email = dto.getEmail();

        this.avatar = dto.getAvatar();
        this.description = dto.getDescription();
    }

    public User(JaccountUserDTO dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.avatar = dto.getAvatar();
        this.description = dto.getDescription();
        this.account = dto.getAccount();
    }

    public User() {
        // not used
    }
}
