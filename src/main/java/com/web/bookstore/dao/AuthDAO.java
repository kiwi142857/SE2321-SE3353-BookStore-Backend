package com.web.bookstore.dao;

import com.web.bookstore.model.Auth;
import com.web.bookstore.model.User;
import com.web.bookstore.repository.AuthRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthDAO {

    private final AuthRepository authRepository;

    public AuthDAO(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public Optional<Auth> findByToken(String token) {
        return authRepository.findByToken(token);
    }

    public Optional<Auth> findByUser(User user) {
        return authRepository.findByUser(user);
    }

    public void save(Auth auth) {
        authRepository.save(auth);
    }
}
