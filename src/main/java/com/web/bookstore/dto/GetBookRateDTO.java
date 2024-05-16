package com.web.bookstore.dto;

import jakarta.persistence.criteria.CriteriaBuilder.In;

@lombok.Data
public class GetBookRateDTO {

    Integer rate;

    public GetBookRateDTO() {
    }

    public GetBookRateDTO(Integer rate) {
        this.rate = rate;
    }
}
