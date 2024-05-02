package com.web.bookstore.repository;

import com.web.bookstore.model.Auth;
import com.web.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Integer> {
    public Optional<Auth> findByToken(String token);

    public Optional<Auth> findByUser(User user);
}
