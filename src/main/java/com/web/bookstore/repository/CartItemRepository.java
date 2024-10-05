package com.web.bookstore.repository;

import com.web.bookstore.model.CartItem;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.bookstore.model.Cart;
import com.web.bookstore.model.Book;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Repository
public interface CartItemRepository extends PagingAndSortingRepository<CartItem, Integer> {

    List<CartItem> findByCart(Cart cart);

    Page<CartItem> findByCart(Cart cart, Pageable pageable);

    Optional<CartItem> findByCartAndBook(Cart cart, Book book);

    Optional<CartItem> findById(Integer id);

    List<CartItem> findAllById(Iterable<Integer> ids);

    List<CartItem> findAllByBook(Book book);

    void save(CartItem cartItem);

    void delete(CartItem cartItem);
}
