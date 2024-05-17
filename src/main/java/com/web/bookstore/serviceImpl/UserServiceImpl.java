package com.web.bookstore.serviceimpl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

import com.web.bookstore.model.User;
import com.web.bookstore.model.Auth;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.dto.UpdateUserInfoRequestDTO;
import com.web.bookstore.repository.UserRepository;
import com.web.bookstore.service.AuthService;
import com.web.bookstore.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final AuthService authService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, AuthService authService, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserById(Integer id) {
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NoSuchElementException("User not found for ID: " + id);
        }

        return optionalUser.get();
    }

    public ResponseDTO updateUserInfo(
            UpdateUserInfoRequestDTO updateUserInfoRequestDTO,
            String token) throws AuthenticationException {
        User requestUser = authService.getUserByToken(token);
        String name = updateUserInfoRequestDTO.getName();
        Optional<User> optionalUser = repository.findByName(name);
        if (optionalUser.isPresent() && !(optionalUser.get().getId() == requestUser.getId())) {
            throw new IllegalArgumentException("Username already exists");
        }
        requestUser.setName(updateUserInfoRequestDTO.getName());
        requestUser.setDescription(updateUserInfoRequestDTO.getDescription());

        repository.save(requestUser);
        return new ResponseDTO(true, "Update user info successfully");
    }

    public ResponseDTO changePassword(String token, String oldPassword, String newPassword) {
        User user = authService.getUserByToken(token);
        Optional<Auth> optionalAuth = authService.getAuthByToken(token);

        if (optionalAuth.isEmpty()) {
            throw new NoSuchElementException("Auth not found for token: " + token);
        }
        if (!passwordEncoder.matches(oldPassword, optionalAuth.get().getPassword())) {
            System.out.println("Old password is incorrect");
            return new ResponseDTO(false, "Old password is incorrect");
        }

        optionalAuth.get().setPassword(newPassword);
        repository.save(user);
        return new ResponseDTO(true, "Change password successfully");
    }
}
