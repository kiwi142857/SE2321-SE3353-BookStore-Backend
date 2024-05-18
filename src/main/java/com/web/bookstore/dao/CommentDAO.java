package com.web.bookstore.dao;

import com.web.bookstore.model.Comment;
import com.web.bookstore.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentDAO {

    private final CommentRepository commentRepository;

    public CommentDAO(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Optional<Comment> findById(Integer id) {
        return commentRepository.findById(id);
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}