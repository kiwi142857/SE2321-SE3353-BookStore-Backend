package com.web.bookstore.dto;

import java.util.List;

import lombok.Data;

@Data
public class PostOrderDTO {

    String receiver;

    String address;

    String tel;

    List<Integer> items;
}
