package com.web.bookstore.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.bookstore.model.Book;
import com.web.bookstore.model.Cart;
import com.web.bookstore.model.CartItem;

public interface CartItemDAO {

    List<CartItem> findByCart(Cart cart);

    Page<CartItem> findByCart(Cart cart, Pageable pageable);

    Optional<CartItem> findByCartAndBook(Cart cart, Book book);

    Optional<CartItem> findById(Integer id);

    List<CartItem> findAllById(Iterable<Integer> ids);

    List<CartItem> findAllByBook(Book book);

    void save(CartItem cartItem);

    void delete(CartItem cartItem);

    void deleteById(Integer id);

    void flush();

}
