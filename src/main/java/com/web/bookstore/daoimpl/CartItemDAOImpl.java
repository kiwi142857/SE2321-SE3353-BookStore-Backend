package com.web.bookstore.daoimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.bookstore.dao.CartItemDAO;
import com.web.bookstore.model.Book;
import com.web.bookstore.model.Cart;
import com.web.bookstore.model.CartItem;
import com.web.bookstore.repository.CartItemRepository;

@Service
public class CartItemDAOImpl implements CartItemDAO {

    private final CartItemRepository cartItemRepository;

    public CartItemDAOImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override

    public List<CartItem> findByCart(Cart cart) {
        return cartItemRepository.findByCart(cart);
    }

    @Override
    public Page<CartItem> findByCart(Cart cart, Pageable pageable) {
        return cartItemRepository.findByCart(cart, pageable);
    }

    @Override
    public Optional<CartItem> findByCartAndBook(Cart cart, Book book) {
        return cartItemRepository.findByCartAndBook(cart, book);
    }

    @Override
    public Optional<CartItem> findById(Integer id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public List<CartItem> findAllByBook(Book book) {
        return cartItemRepository.findAllByBook(book);
    }

    @Override
    public List<CartItem> findAllById(Iterable<Integer> ids) {
        return cartItemRepository.findAllById(ids);
    }

    @Override
    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Transactional
    @Override
    public void delete(CartItem cartItem) {
        System.out.println("Deleting cart item: " + cartItem.getId());
        try {
            cartItemRepository.delete(cartItem);
            cartItemRepository.flush();
        } catch (Exception e) {
            System.out.println("Error deleting cart item: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // delete by id
    @Transactional
    @Override
    public void deleteById(Integer id) {
        cartItemRepository.deleteById(id);
        System.out.println("Deleted cart item with id: " + id);
        cartItemRepository.flush();
    }

    @Override
    public void flush() {
        cartItemRepository.flush();
    }

}
