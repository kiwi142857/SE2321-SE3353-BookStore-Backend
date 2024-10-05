package com.web.bookstore.repository;

import com.web.bookstore.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    public Optional<Comment> findById(Integer id);
}