package com.web.bookstore.dto;

import com.web.bookstore.model.OrderItem;

import lombok.Data;

@Data
public class OrderItemDTO {

    public Integer id;
    public OrderBookDTO book;
    public Integer number;

    public OrderItemDTO() {
    }

    public OrderItemDTO(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.book = new OrderBookDTO(orderItem);
        this.number = orderItem.getNumber();
    }

}
