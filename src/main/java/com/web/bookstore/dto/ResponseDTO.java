package com.web.bookstore.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    private boolean ok;
    private String message;

    public ResponseDTO(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }
}
