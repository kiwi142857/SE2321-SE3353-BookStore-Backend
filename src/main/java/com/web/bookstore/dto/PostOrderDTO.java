package com.web.bookstore.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PostOrderDTO {

    String receiver;

    String address;

    String tel;

    @JsonProperty("itemIds")
    List<Integer> items;
}
