package com.web.microserivce.book.dto;

import lombok.Data;

@Data
public class AuthorDTO {

    String author;

    public AuthorDTO(String author) {
        this.author = author;
    }
}
