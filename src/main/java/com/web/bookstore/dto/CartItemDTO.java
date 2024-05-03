package com.web.bookstore.dto;

import com.web.bookstore.model.Book;
import com.web.bookstore.model.CartItem;

import lombok.Data;

@Data
public class CartItemDTO {

    private Integer id;

    private Book book;

    private Integer number;

    public CartItemDTO() {
    }

    public CartItemDTO(CartItem cartItem) {
        this.id = cartItem.getId();
        this.book = cartItem.getBook();
        this.number = cartItem.getNumber();
    }
}
