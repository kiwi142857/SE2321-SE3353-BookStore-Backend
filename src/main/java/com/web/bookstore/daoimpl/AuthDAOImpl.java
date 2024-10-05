package com.web.bookstore.daoimpl;

import com.web.bookstore.model.Auth;
import com.web.bookstore.model.User;
import com.web.bookstore.dao.AuthDAO;
import com.web.bookstore.repository.AuthRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthDAOImpl implements AuthDAO {

    private final AuthRepository authRepository;

    public AuthDAOImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public Optional<Auth> findByUser(User user) {
        return authRepository.findByUser(user);
    }

    public void save(Auth auth) {
        authRepository.save(auth);
    }
}
