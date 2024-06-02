package com.web.bookstore.dto;

@lombok.Data
public class GetBookRateDTO {

    Integer rate;

    public GetBookRateDTO() {
    }

    public GetBookRateDTO(Integer rate) {
        this.rate = rate;
    }
}
