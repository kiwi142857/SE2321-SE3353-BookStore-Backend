package com.web.bookstore.serviceimpl;

import org.springframework.stereotype.Service;

import com.web.bookstore.dao.CommentDAO;
import com.web.bookstore.model.Comment;
import com.web.bookstore.service.CommentService;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDAO commentDAO;

    public CommentServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public Optional<Comment> getCommentById(Integer id) {
        return commentDAO.findById(id);
    }

}
