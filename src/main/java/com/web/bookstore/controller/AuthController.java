package com.web.bookstore.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

import org.json.JSONObject;

import com.web.bookstore.dto.LoginOkResponseDTO;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.service.AuthService;
import com.web.bookstore.dto.LoginRequestDTO;
import com.web.bookstore.dto.RegisterRequestDTO;
import com.web.bookstore.util.SessionUtils;
import com.web.bookstore.model.Auth;
import com.web.bookstore.model.User;
import com.web.bookstore.exception.UserBannedException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO loginDTO) {
        try {
            // 更新Cookies
            Auth auth = service.login(loginDTO);
            User user = auth.getUser();
            SessionUtils.setSession(user);
            if (user.getStatus() == 1) {
                throw new UserBannedException("User is banned");
            }

            return ResponseEntity.ok(new LoginOkResponseDTO(true, "login success"));
        } catch (UserBannedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseDTO(false, e.getMessage()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequestDTO registerDTO) {
        try {
            service.register(registerDTO);
            return ResponseEntity.ok(new ResponseDTO(true, "register success"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @PutMapping("/logout")
    public ResponseEntity<Object> logout(HttpSession session) {

        session.invalidate();

        return ResponseEntity.ok(new ResponseDTO(true, "logout success"));
    }

}
