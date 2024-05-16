package com.web.bookstore.dto;

import lombok.Data;

import com.web.bookstore.model.Comment;

@Data
public class CommentDTO {

    String id;
    String content;
    String createdAt;
    String username;
    String reply;

    public CommentDTO(String id, String content, String createdAt, String username, String reply) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.username = username;
        this.reply = reply;
    }

    public CommentDTO() {
    }

    public CommentDTO(Comment comment) {
        this.id = comment.getId().toString();
        this.content = comment.getContent();
        this.createdAt = comment.getDate().toString();
        this.username = comment.getUser().getName();
        this.reply = comment.getReply();
    }
}
