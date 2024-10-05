package com.web.bookstore.repository;

import com.web.bookstore.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByName(String name);

    public Optional<User> findByAccount(String account);

    @Query("SELECT u FROM User u JOIN u.auth a WHERE u.name = :name AND a.password = :password")
    Optional<User> findByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Query("SELECT u FROM User u WHERE u.name LIKE CONCAT('%', :name, '%')")
    public Page<User> findUsersContainingName(@Param("name") String name, Pageable pageable);
}