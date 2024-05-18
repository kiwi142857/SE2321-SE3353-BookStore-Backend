package com.web.bookstore.dao;

import com.web.bookstore.model.Book;
import com.web.bookstore.model.Cart;
import com.web.bookstore.model.CartItem;
import com.web.bookstore.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemDAO {

    private final CartItemRepository cartItemRepository;

    public CartItemDAO(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> findByCart(Cart cart) {
        return cartItemRepository.findByCart(cart);
    }

    public Optional<CartItem> findByCartAndBook(Cart cart, Book book) {
        return cartItemRepository.findByCartAndBook(cart, book);
    }

    public Optional<CartItem> findById(Integer id) {
        return cartItemRepository.findById(id);
    }

    public List<CartItem> findAllById(Iterable<Integer> ids) {
        return cartItemRepository.findAllById(ids);
    }

}