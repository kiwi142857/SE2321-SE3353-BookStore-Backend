package com.web.bookstore.service;

import com.web.bookstore.model.Comment;

import java.util.Optional;

public interface CommentService {
    public Optional<Comment> getCommentById(Integer id);
}
