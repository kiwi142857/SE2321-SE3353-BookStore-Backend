package com.web.bookstore.dto;

import com.web.bookstore.model.BookRate;

import lombok.Data;

@Data
public class BookRateJsonDTO {

    private Integer id;

    private Integer rate;

    public BookRateJsonDTO() {
    }

    public BookRateJsonDTO(Integer id, Integer rate) {
        this.id = id;
        this.rate = rate;
    }

    public BookRateJsonDTO(BookRate bookRate) {
        this.id = bookRate.getId();
        this.rate = bookRate.getRate();
    }

}
