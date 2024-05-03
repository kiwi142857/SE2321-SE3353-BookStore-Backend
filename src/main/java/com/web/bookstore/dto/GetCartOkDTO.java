package com.web.bookstore.dto;

import com.web.bookstore.model.CartItem;

import lombok.Data;

import java.util.List;

import java.util.stream.Collectors;

import com.web.bookstore.model.Cart;

@Data
public class GetCartOkDTO {

    List<CartItemDTO> cartItems;

    public GetCartOkDTO(Cart cart) {
        this.cartItems = cart.getItems().stream().map(CartItemDTO::new).collect(Collectors.toList());
    }
}
