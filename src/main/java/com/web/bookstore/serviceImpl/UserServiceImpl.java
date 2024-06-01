package com.web.bookstore.serviceimpl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import com.web.bookstore.dao.UserDAO;
import com.web.bookstore.dto.UserDTO;
import com.web.bookstore.dto.GetUserListOk;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final AuthService authService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAO userDAO, AuthService authService, BCryptPasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserById(Integer id) {
        Optional<User> optionalUser = userDAO.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NoSuchElementException("User not found for ID: " + id);
        }

        return optionalUser.get();
    }

    public ResponseDTO updateUserInfo(
            UpdateUserInfoRequestDTO updateUserInfoRequestDTO,
            User requestUser) throws AuthenticationException {
        String name = updateUserInfoRequestDTO.getName();
        Optional<User> optionalUser = userDAO.findByName(name);
        if (optionalUser.isPresent() && !(optionalUser.get().getId() == requestUser.getId())) {
            throw new IllegalArgumentException("Username already exists");
        }
        requestUser.setName(updateUserInfoRequestDTO.getName());
        requestUser.setDescription(updateUserInfoRequestDTO.getDescription());

        userDAO.save(requestUser);
        return new ResponseDTO(true, "Update user info successfully");
    }

    public ResponseDTO changePassword(User user, String oldPassword, String newPassword) {

        Optional<Auth> optionalAuth = authService.findByUser(user);

        if (optionalAuth.isEmpty()) {
            throw new NoSuchElementException("Auth not found for user: " + user.getId());
        }
        if (!passwordEncoder.matches(oldPassword, optionalAuth.get().getPassword())) {
            System.out.println("Old password is incorrect");
            return new ResponseDTO(false, "Old password is incorrect");
        }

        optionalAuth.get().setPassword(newPassword);
        userDAO.save(user);
        return new ResponseDTO(true, "Change password successfully");
    }

    public GetUserListOk getUserList(Integer pageSize, Integer pageIndex, String keyWord, Integer id) {

        PageRequest pageable = PageRequest.of(pageIndex, pageSize);

        if (id != -1) {
            Optional<User> optionalUser = userDAO.findById(id);
            if (optionalUser.isEmpty()) {
                throw new NoSuchElementException("User not found for ID: " + id);
            }
            List<User> userList = new ArrayList<>();
            userList.add(optionalUser.get());
            return new GetUserListOk(userList, 1);
        }

        Page<User> userPage = userDAO.findByNameContaining(keyWord, pageable);
        List<User> userList = userPage.stream().collect(Collectors.toList());
        return new GetUserListOk(userList, (int) userPage.getTotalElements());
    }

    public ResponseDTO banUser(User targetUser) {
        targetUser.setStatus(1);
        userDAO.save(targetUser);
        return new ResponseDTO(true, "Ban user successfully");
    }

    public ResponseDTO unbanUser(User targetUser) {
        targetUser.setStatus(0);
        userDAO.save(targetUser);
        return new ResponseDTO(true, "Unban user successfully");
    }
}
