package com.web.bookstore.daoimpl;

import com.web.bookstore.model.Book;
import com.web.bookstore.model.Cart;
import com.web.bookstore.model.CartItem;
import com.web.bookstore.dao.CartItemDAO;
import com.web.bookstore.repository.CartItemRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemDAOImpl implements CartItemDAO {

    private final CartItemRepository cartItemRepository;

    public CartItemDAOImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> findByCart(Cart cart) {
        return cartItemRepository.findByCart(cart);
    }

    public Page<CartItem> findByCart(Cart cart, Pageable pageable) {
        return cartItemRepository.findByCart(cart, pageable);
    }

    public Optional<CartItem> findByCartAndBook(Cart cart, Book book) {
        return cartItemRepository.findByCartAndBook(cart, book);
    }

    public Optional<CartItem> findById(Integer id) {
        return cartItemRepository.findById(id);
    }

    public List<CartItem> findAllByBook(Book book) {
        return cartItemRepository.findAllByBook(book);
    }

    public List<CartItem> findAllById(Iterable<Integer> ids) {
        return cartItemRepository.findAllById(ids);
    }

    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public void delete(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }
}