package com.web.bookstore.dto;

import java.util.List;
import java.util.ArrayList;

import lombok.Data;

import com.web.bookstore.dto.CommentDTO;
import com.web.bookstore.model.Comment;

@Data
public class GetCommentListDTO {

    private Integer total;
    List<CommentDTO> items;

    public GetCommentListDTO() {
    }

    public GetCommentListDTO(Integer total, List<Comment> comments) {
        this.total = total;
        items = new ArrayList<>();
        for (Comment comment : comments) {
            items.add(new CommentDTO(comment));
        }
    }
}
