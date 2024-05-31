package com.web.bookstore.serviceimpl;

import com.web.bookstore.dto.RegisterRequestDTO;
import com.web.bookstore.service.AuthService;

import com.web.bookstore.dto.LoginRequestDTO;
import com.web.bookstore.model.Auth;
import com.web.bookstore.model.User;
import com.web.bookstore.repository.AuthRepository;
import com.web.bookstore.repository.UserRepository;
import com.web.bookstore.dao.AuthDAO;
import com.web.bookstore.dao.UserDAO;
import com.web.bookstore.dto.JaccountResponseDTO;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import javax.naming.AuthenticationException;
import java.util.NoSuchElementException;
import java.util.Optional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Date;

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
        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        Optional<User> optionalUser = userDAO.findByNameAndPassword(dto.getUsername(), hashedPassword);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Optional<Auth> optionalAuth = authDAO.findByUser(user);
            if (optionalAuth.isPresent()) {
                return optionalAuth.get();
            }
        }
        throw new AuthenticationException("Invalid username or password");
    }

    public void register(RegisterRequestDTO dto) {
        if (userDAO.findByName(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        User user = new User(dto);
        userDAO.save(user);
        Auth auth = new Auth(user, passwordEncoder.encode(dto.getPassword()));
        authDAO.save(auth);

    }

    public Optional<Auth> findByUser(User user) {
        return authDAO.findByUser(user);
    }
}
