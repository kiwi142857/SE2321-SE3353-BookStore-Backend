package com.web.bookstore.dao;

import com.web.bookstore.model.Auth;
import com.web.bookstore.model.User;

import java.util.Optional;

public interface AuthDAO {

    Optional<Auth> findByUser(User user);

    void save(Auth auth);
}