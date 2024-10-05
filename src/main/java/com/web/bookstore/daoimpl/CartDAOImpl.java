package com.web.bookstore.daoimpl;

import com.web.bookstore.repository.CartRepository;

import org.springframework.stereotype.Service;

import com.web.bookstore.model.Cart;
import com.web.bookstore.dao.CartDAO;

@Service
public class CartDAOImpl implements CartDAO {

    private final CartRepository cartRepository;

    public CartDAOImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void save(Cart cart) {
        cartRepository.save(cart);
    }
}
