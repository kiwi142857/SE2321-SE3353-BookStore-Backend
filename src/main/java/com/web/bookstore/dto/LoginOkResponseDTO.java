package com.web.bookstore.dto;

import lombok.Data;

@Data
public class LoginOkResponseDTO {
    private Boolean ok;
    private String message;

    public LoginOkResponseDTO(Boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }
}
