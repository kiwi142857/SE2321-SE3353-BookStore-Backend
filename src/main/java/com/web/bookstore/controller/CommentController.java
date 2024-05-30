package com.web.bookstore.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.web.bookstore.model.Book;
import com.web.bookstore.service.BookService;
import com.web.bookstore.service.CommentService;
import com.web.bookstore.util.SessionUtils;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.web.bookstore.dto.CommentRequestDTO;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.model.Comment;
import com.web.bookstore.model.User;

import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final BookService bookService;
    private final CommentService commentService;

    public CommentController(BookService bookService, CommentService commentService) {
        this.bookService = bookService;
        this.commentService = commentService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> replyComment(@PathVariable Integer id, @RequestBody CommentRequestDTO request) {
        try {
            User user = SessionUtils.getUser();
            Optional<Comment> comment = commentService.getCommentById(id);
            if (comment.isPresent()) {
                Integer bookId = comment.get().getBook().getId();
                String reply = comment.get().getUser().getName();
                return ResponseEntity
                        .ok(bookService.replyComment(user, bookId, request.getContent(), reply));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "Comment not found"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, e.getMessage()));
        }
    }

}
