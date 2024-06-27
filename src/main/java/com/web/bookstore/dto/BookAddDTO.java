package com.web.bookstore.dto;

import lombok.Data;

@Data
public class BookAddDTO {

    boolean ok;
    String message;
    Integer bookId;

    public BookAddDTO() {
    }

    public BookAddDTO(boolean ok, String message, Integer bookId) {
        this.ok = ok;
        this.bookId = bookId;
        this.message = message;
    }
}
