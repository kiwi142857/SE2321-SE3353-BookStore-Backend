package com.web.bookstore.dto;

import java.io.Serializable;

import com.web.bookstore.model.BookRate;

import lombok.Data;

@Data
public class BookRateJsonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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
