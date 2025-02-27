package com.web.bookstore.dto;

import com.web.bookstore.model.CartItem;

import lombok.Data;

import java.util.List;

import java.util.stream.Collectors;

import java.util.ArrayList;
import com.web.bookstore.model.Cart;

@Data
public class GetCartOkDTO {

    List<CartItemDTO> cartItems;
    int total;

    public GetCartOkDTO(Cart cart) {
        if (cart.getItems() != null) {
            this.cartItems = cart.getItems().stream().map(CartItemDTO::new).collect(Collectors.toList());
        } else {
            this.cartItems = new ArrayList<>();
        }
    }

    public void setTotalItems(int totalItems) {
        this.total = totalItems;
    }

    public GetCartOkDTO(List<CartItem> cartItems) {
        if (cartItems != null) {
            this.cartItems = cartItems.stream().map(CartItemDTO::new).collect(Collectors.toList());
        } else {
            this.cartItems = new ArrayList<>();
        }
    }

    public GetCartOkDTO(List<CartItem> cartItems, int total) {
        if (cartItems != null) {
            this.cartItems = cartItems.stream().map(CartItemDTO::new).collect(Collectors.toList());
        } else {
            this.cartItems = new ArrayList<>();
        }
        this.total = total;
    }
}
