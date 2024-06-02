package com.web.bookstore.dto;

import com.web.bookstore.model.OrderItem;

import lombok.Data;

@Data
public class OrderItemDTO {

    public Integer id;
    public GetBookDetailDTO book;
    public Integer number;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Integer id, GetBookDetailDTO book, Integer number) {
        this.id = id;
        this.book = book;
        this.number = number;
    }

    public OrderItemDTO(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.book = new GetBookDetailDTO(orderItem.getBook());
        this.number = orderItem.getNumber();
    }

}
