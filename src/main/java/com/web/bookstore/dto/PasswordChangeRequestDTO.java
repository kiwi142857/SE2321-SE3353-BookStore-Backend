package com.web.bookstore.dto;

@lombok.Data
public class PasswordChangeRequestDTO {
    private String oldPassword;
    private String newPassword;
}
