package com.web.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.NoSuchElementException;

import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.dto.UpdateUserInfoRequestDTO;
import com.web.bookstore.dto.UserDTO;
import com.web.bookstore.model.User;
import com.web.bookstore.dto.PasswordChangeRequestDTO;
import com.web.bookstore.service.AuthService;
import com.web.bookstore.service.UserService;
import com.web.bookstore.util.SessionUtils;

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
    public ResponseEntity<Object> getMyProfile() {

        try {
            User user = SessionUtils.getUser();
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ResponseDTO(false, "Please login first"));
            }
            return ResponseEntity.ok(new UserDTO(user));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @PutMapping("/me")
    public ResponseEntity<Object> updateUserProfile(
            @RequestBody UpdateUserInfoRequestDTO updateUserInfoRequestDTO) {
        try {
            User user = SessionUtils.getUser();
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ResponseDTO(false, "Please login first"));
            }
            return ResponseEntity.ok(service.updateUserInfo(updateUserInfoRequestDTO, user));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(false, e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @PutMapping("/me/password")
    public ResponseEntity<Object> changePassword(
            @RequestBody PasswordChangeRequestDTO request) {
        try {
            User user = SessionUtils.getUser();
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ResponseDTO(false, "Please login first"));
            }
            return ResponseEntity.ok(service.changePassword(user, request.getOldPassword(), request.getNewPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(false, e.getMessage()));
        }
    }
}
