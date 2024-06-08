package com.web.bookstore.dao;

import com.web.bookstore.model.Book;
import com.web.bookstore.model.Cart;
import com.web.bookstore.model.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CartItemDAO {

    List<CartItem> findByCart(Cart cart);

    Page<CartItem> findByCart(Cart cart, Pageable pageable);

    Optional<CartItem> findByCartAndBook(Cart cart, Book book);

    Optional<CartItem> findById(Integer id);

    List<CartItem> findAllById(Iterable<Integer> ids);

    void save(CartItem cartItem);

    void delete(CartItem cartItem);
}