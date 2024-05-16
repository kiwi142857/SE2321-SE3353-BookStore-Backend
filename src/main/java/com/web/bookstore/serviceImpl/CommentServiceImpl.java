package com.web.bookstore.serviceimpl;

import org.springframework.stereotype.Service;

import com.web.bookstore.model.Comment;
import com.web.bookstore.repository.CommentRepository;
import com.web.bookstore.service.CommentService;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<Comment> getCommentById(Integer id) {
        return commentRepository.findById(id);
    }

}
