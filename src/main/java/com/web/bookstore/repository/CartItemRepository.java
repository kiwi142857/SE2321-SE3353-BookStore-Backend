package com.web.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.bookstore.model.Book;
import com.web.bookstore.model.Cart;
import com.web.bookstore.model.CartItem;

@Repository
public interface CartItemRepository extends PagingAndSortingRepository<CartItem, Integer> {

    List<CartItem> findByCart(Cart cart);

    Page<CartItem> findByCart(Cart cart, Pageable pageable);

    Optional<CartItem> findByCartAndBook(Cart cart, Book book);

    Optional<CartItem> findById(Integer id);

    List<CartItem> findAllById(Iterable<Integer> ids);

    List<CartItem> findAllByBook(Book book);

    void save(CartItem cartItem);

    @Transactional
    void delete(CartItem cartItem);

    @Transactional
    void deleteById(Integer id);

    @Transactional
    void flush();

}
