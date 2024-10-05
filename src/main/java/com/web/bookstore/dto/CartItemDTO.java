package com.web.bookstore.dto;

import com.web.bookstore.model.CartItem;

import lombok.Data;

@Data
public class CartItemDTO {

    private Integer id;

    private BookBreifDTO book;

    private Integer number;

    public CartItemDTO() {
    }

    public CartItemDTO(CartItem cartItem) {
        this.id = cartItem.getId();
        this.book = new BookBreifDTO(cartItem.getBook());
        this.number = cartItem.getNumber();
    }
}
