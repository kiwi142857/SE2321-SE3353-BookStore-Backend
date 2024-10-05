package com.web.bookstore.daoimpl;

import com.web.bookstore.model.User;
import com.web.bookstore.repository.UserRepository;
import com.web.bookstore.dao.UserDAO;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class UserDAOImpl implements UserDAO {

    private final UserRepository userRepository;

    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public Optional<User> findByAccount(String account) {
        return userRepository.findByAccount(account);
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByNameAndPassword(String name, String password) {
        System.out.println("name: " + name + " password: " + password);
        return userRepository.findByNameAndPassword(name, password);
    }

    public Page<User> findByNameContaining(String name, Pageable pageable) {
        return userRepository.findUsersContainingName(name, pageable);
    }
}