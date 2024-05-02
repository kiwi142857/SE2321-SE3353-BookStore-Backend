package com.web.bookstore.repository;

import com.web.bookstore.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByName(String name);

    public Optional<User> findByAccount(String account);
}