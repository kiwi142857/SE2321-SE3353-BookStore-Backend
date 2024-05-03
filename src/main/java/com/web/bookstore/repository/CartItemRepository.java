package com.web.bookstore.repository;

import com.web.bookstore.model.CartItem;
import com.web.bookstore.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.web.bookstore.model.Cart;
import com.web.bookstore.model.Book;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    List<CartItem> findByCart(Cart cart);

    Optional<CartItem> findByCartAndBook(Cart cart, Book book);

    Optional<CartItem> findById(Integer id);

    List<CartItem> findAllById(Iterable<Integer> ids);
}
