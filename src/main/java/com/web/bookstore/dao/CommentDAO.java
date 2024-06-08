package com.web.bookstore.dao;

import com.web.bookstore.model.Comment;

import java.util.Optional;

public interface CommentDAO {

    Optional<Comment> findById(Integer id);

    void save(Comment comment);
}