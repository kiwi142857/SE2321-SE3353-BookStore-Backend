package com.web.bookstore.daoimpl;

import com.web.bookstore.model.Comment;
import com.web.bookstore.dao.CommentDAO;
import com.web.bookstore.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentDAOImpl implements CommentDAO {

    private final CommentRepository commentRepository;

    public CommentDAOImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Optional<Comment> findById(Integer id) {
        return commentRepository.findById(id);
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}