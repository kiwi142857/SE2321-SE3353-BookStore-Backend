package com.web.bookstore.serviceimpl;

import com.web.bookstore.dto.RegisterRequestDTO;
import com.web.bookstore.service.AuthService;

import com.web.bookstore.dto.LoginRequestDTO;
import com.web.bookstore.model.Auth;
import com.web.bookstore.model.User;
import com.web.bookstore.repository.AuthRepository;
import com.web.bookstore.repository.UserRepository;
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

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthRepository authRepository, UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(LoginRequestDTO dto) throws AuthenticationException {
        Optional<User> optionalUser = userRepository.findByName(dto.getUsername());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Optional<Auth> optionalAuth = authRepository.findByUser(user);
            if (optionalAuth.isEmpty()) {
                throw new AuthenticationException("Invalid username or password");
            }
            Auth auth = optionalAuth.get();
            if (passwordEncoder.matches(dto.getPassword(), auth.getPassword())) {
                return generateAuthToken(user);
            }
        }
        throw new AuthenticationException("Invalid username or password");

    }

    public void register(RegisterRequestDTO dto) {
        if (userRepository.findByName(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        User user = new User(dto);
        userRepository.save(user);
        Auth auth = new Auth(user, passwordEncoder.encode(dto.getPassword()));
        authRepository.save(auth);

    }

    private String generateAuthToken(User user) {
        Optional<Auth> optionalAuth = authRepository.findByUser(user);
        Auth auth;
        if (optionalAuth.isPresent()) {
            // 用户已经拥有令牌
            auth = optionalAuth.get();
            auth.updateToken();
        } else {
            // 用户没有令牌
            auth = new Auth(user);
        }

        authRepository.save(auth);
        return auth.getToken();
    }

    public User getUserByToken(String token) {
        // TODO: 登录请求应该抛出401(未授权)异常
        Auth auth = authRepository.findByToken(token)
                .orElseThrow(() -> new NoSuchElementException("Invalid token"));
        return auth.getUser();
    }

    public String requestAccessToken(String code) throws AuthenticationException {
        String client_id = "ov3SLrO4HyZSELxcHiqS";
        String client_secret = "B9919DDA3BD9FBF7ADB9F84F67920D8CB6528620B9586D1C";
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = null;
        try {
            response = Unirest.post("http://jaccount.sjtu.edu.cn/oauth2/token")
                    .header("Authorization",
                            "Basic b3YzU0xyTzRIeVpTRUx4Y0hpcVM6Qjk5MTlEREEzQkQ5RkJGN0FEQjlGODRGNjc5MjBEOENCNjUyODYyMEI5NTg2RDFD")
                    .header("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Accept", "*/* ")
                    .header("Host", "jaccount.sjtu.edu.cn")
                    .header("Connection", "keep-alive")
                    .field("grant_type", "authorization_code")
                    .field("code", code)
                    .field("client_id", client_id)
                    .field("client_secret", client_secret)
                    .field("redirect_uri", "http://localhost:3000/login")
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        JSONObject responseBody = null;
        try {
            responseBody = new JSONObject(response.getBody());
        } catch (JSONException e) {
            throw new AuthenticationException("Request access token failed");
        }
        String accessToken;
        try {
            accessToken = responseBody.getString("access_token");
        } catch (JSONException e) {
            throw new AuthenticationException("Request access token failed");
        }
        return accessToken;
    }

    public String jaccountLogin(String code) throws AuthenticationException {
        String accessToken;
        try {
            accessToken = requestAccessToken(code);
        } catch (AuthenticationException e) {
            throw new AuthenticationException("Jaccount login failed");
        }

        User user = sendGetRequest("https://api.sjtu.edu.cn/v1/me/profile?access_token=" + accessToken);

        // 查找是否已经注册
        Optional<User> optionalUser = userRepository.findByAccount(user.getAccount());
        if (optionalUser.isPresent()) {
            return generateAuthToken(optionalUser.get());
        }
        userRepository.save(user);
        String token = generateAuthToken(user);
        System.out.println("token" + token);
        return token;
    }

    private User sendGetRequest(String urlStr) throws AuthenticationException {
        try {
            Unirest.setTimeouts(300, 3000);
            HttpResponse<String> response = Unirest.get(urlStr)
                    .header("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                    .header("Accept", "*/*")
                    .header("Host", "api.sjtu.edu.cn")
                    .header("Connection", "keep-alive")
                    .asString();
            if (response.getStatus() == HttpURLConnection.HTTP_OK) {
                ObjectMapper mapper = new ObjectMapper();
                JaccountResponseDTO jaccountResponse = mapper.readValue(response.getBody(), JaccountResponseDTO.class);
                return new User(jaccountResponse.getUsers().get(0));
            } else {
                throw new AuthenticationException("GET request not worked");
            }
        } catch (Exception e) {
            throw new AuthenticationException("Sending GET request failed");
        }
    }

    public Optional<Auth> getAuthByToken(String token) {
        return authRepository.findByToken(token);
    }
}
