package com.web.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.NoSuchElementException;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.dto.UpdateUserInfoRequestDTO;
import com.web.bookstore.dto.UserDTO;
import com.web.bookstore.service.AuthService;
import com.web.bookstore.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;
    private final AuthService authService;

    public UserController(UserService service, AuthService authService) {
        this.service = service;
        this.authService = authService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserProfile(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(new UserDTO(service.findUserById(id)));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Object> getMyProfile(@CookieValue(value = "token") String token) {
        System.out.println("token: " + token);
        try {
            return ResponseEntity.ok(new UserDTO(authService.getUserByToken(token)));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUserProfile(
            @PathVariable Integer id,
            @CookieValue(value = "token") String token,
            @RequestBody UpdateUserInfoRequestDTO updateUserInfoRequestDTO) {
        try {
            service.updateUserInfo(id, updateUserInfoRequestDTO, token);
            return ResponseEntity.ok(new ResponseDTO(true, "Update user info successfully"));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(false, e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, e.getMessage()));
        }
    }

}
