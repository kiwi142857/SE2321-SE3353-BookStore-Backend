package com.web.bookstore.dao;

import com.web.bookstore.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserDAO {

    Optional<User> findByName(String name);

    Optional<User> findByAccount(String account);

    Optional<User> findById(Integer id);

    void save(User user);

    Optional<User> findByNameAndPassword(String name, String password);

    Page<User> findByNameContaining(String name, Pageable pageable);
}