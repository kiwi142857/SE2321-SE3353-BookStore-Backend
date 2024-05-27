package com.web.bookstore.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO loginDTO, HttpServletResponse response) {
        try {
            // 更新Cookies
            String token = service.login(loginDTO);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(24 * 60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
            Auth auth = service.getAuthByToken(token).get();
            SessionUtils.setSession(auth);

            return ResponseEntity.ok(new LoginOkResponseDTO(true, token));
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
    public ResponseEntity<Object> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok(new ResponseDTO(true, ""));
    }

    @PostMapping("/jaccountLogin")
    public ResponseEntity<Object> jaccountLogin(@RequestBody String jsonString, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject(jsonString);
        String code = jsonObject.getString("code");
        System.out.println(code);

        try {
            // 更新Cookies
            String token = service.jaccountLogin(code);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(24 * 60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResponseEntity.ok(new LoginOkResponseDTO(true, token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(false, e.getMessage()));
        }
    }
}
