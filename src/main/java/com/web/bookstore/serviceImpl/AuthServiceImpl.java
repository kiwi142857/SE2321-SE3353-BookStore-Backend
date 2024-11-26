package com.web.bookstore.serviceimpl;

import java.util.Optional;

import javax.naming.AuthenticationException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.bookstore.dao.AuthDAO;
import com.web.bookstore.dao.UserDAO;
import com.web.bookstore.dto.LoginRequestDTO;
import com.web.bookstore.dto.RegisterRequestDTO;
import com.web.bookstore.model.Auth;
import com.web.bookstore.model.User;
import com.web.bookstore.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthDAO authDAO;
    private final UserDAO userDAO;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthDAO authDAO, UserDAO userDAO,
            BCryptPasswordEncoder passwordEncoder) {
        this.authDAO = authDAO;
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public Auth login(LoginRequestDTO dto) throws AuthenticationException {
        Optional<User> optionalUser = userDAO.findByName(dto.getUsername());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Optional<Auth> optionalAuth = authDAO.findByUser(user);
            if (optionalAuth.isPresent()) {
                Auth auth = optionalAuth.get();
                if (passwordEncoder.matches(dto.getPassword(), auth.getPassword())) {
                    return auth;
                }
            }
        }
        throw new AuthenticationException("Invalid username or password");
    }

    public void register(RegisterRequestDTO dto) {
        if (userDAO.findByName(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        User user = new User(dto);
        Auth auth = new Auth(user, passwordEncoder.encode(dto.getPassword()));
        user.setAuth(auth);
        userDAO.save(user);
        authDAO.save(auth);

    }

    public Optional<Auth> findByUser(User user) {
        return authDAO.findByUser(user);
    }
}
