package com.web.bookstore.dao;

import com.web.bookstore.repository.CartRepository;

import org.springframework.stereotype.Service;

import com.web.bookstore.model.Cart;

@Service
public class CartDAO {

    private final CartRepository cartRepository;

    public CartDAO(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void save(Cart cart) {
        cartRepository.save(cart);
    }
}
