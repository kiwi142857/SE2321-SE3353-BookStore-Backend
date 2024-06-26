package com.web.bookstore.dto;

import com.web.bookstore.model.OrderItem;

import lombok.Data;

@Data
public class OrderBookDTO {
    String title;
    String cover;
    Integer price;
    Integer discount;

    public OrderBookDTO(String title, String cover, Integer price, Integer discount) {
        this.title = title;
        this.cover = cover;
        this.price = price;
        this.discount = discount;
    }

    public OrderBookDTO() {
    }

    public OrderBookDTO(OrderItem orderItem) {
        this.title = orderItem.getBookTitle();
        this.cover = orderItem.getBookCover();
        this.price = orderItem.getBookPrice();
        this.discount = orderItem.getBookDiscount();
    }
}
