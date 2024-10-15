package com.web.bookstore.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.web.bookstore.dao.CartDAO;
import com.web.bookstore.dao.CartItemDAO;
import com.web.bookstore.dto.GetCartOkDTO;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.model.Book;
import com.web.bookstore.model.Cart;
import com.web.bookstore.model.CartItem;
import com.web.bookstore.model.User;
import com.web.bookstore.service.BookService;
import com.web.bookstore.service.CartService;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {

    private final CartDAO cartDAO;
    private final CartItemDAO cartItemDAO;
    private final BookService bookService;

    public CartServiceImpl(CartDAO cartDAO, CartItemDAO cartItemDAO, BookService bookService) {
        this.cartDAO = cartDAO;
        this.cartItemDAO = cartItemDAO;
        this.bookService = bookService;
    }

    public ResponseDTO addCartItem(User user, Integer bookId) {

        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cartDAO.save(cart);
            user.setCart(cart);
        }

        // Check if the book is already in the cart
        Optional<Book> book = bookService.getBookById(bookId);
        if (!book.isPresent()) {
            return new ResponseDTO(false, "The book does not exist");
        }

        Book book_ = book.get();

        Optional<CartItem> existingCartItem = cartItemDAO.findByCartAndBook(cart, book_);

        if (existingCartItem.isPresent()) {
            // The book is already in the cart
            return new ResponseDTO(false, "The book is already in the cart");
        } else {
            // The book is not in the cart, add it

            CartItem cartItem = new CartItem(cart, book_);
            cartItemDAO.save(cartItem);
            return new ResponseDTO(true, "The book has been added to the cart");
        }

    }

    @Override
    public ResponseDTO deleteCartItem(User user, Integer cartItemId) {

        System.out.println("Deleting cart item: " + cartItemId + " in CartServiceImpl");
        Cart cart = user.getCart();

        if (cart == null) {
            return new ResponseDTO(false, "The cart is empty");
        }

        Optional<CartItem> cartItem = cartItemDAO.findById(cartItemId);

        if (!cartItem.isPresent()) {
            return new ResponseDTO(false, "The book is not in the cart");
        }

        // Check if the cartItem in the cart
        if (!Objects.equals(cartItem.get().getCart().getId(), cart.getId())) {
            return new ResponseDTO(false, "The book is not in the cart");
        }

        System.out.println("Deleting cart item: " + cartItem.get().getId() + "In deleteCartItem");
        cartItemDAO.delete(cartItem.get());
        return new ResponseDTO(true, "The book has been removed from the cart");

    }

    @Override
    public ResponseDTO updateCartItem(User user, Integer cartItemId, Integer quantity) {

        Cart cart = user.getCart();

        if (cart == null) {
            return new ResponseDTO(false, "The cart is empty");
        }

        Optional<CartItem> cartItem = cartItemDAO.findById(cartItemId);

        if (!cartItem.isPresent()) {
            return new ResponseDTO(false, "The book is not in the cart");
        }

        // Check if the cartItem in the cart
        if (cartItem.get().getCart() != cart) {
            return new ResponseDTO(false, "The book is not in the cart");
        }

        cartItem.get().setNumber(quantity);
        cartItemDAO.save(cartItem.get());
        return new ResponseDTO(true, "The quantity has been updated");

    }

    public GetCartOkDTO getCart(User user, Integer page, Integer size) throws NoSuchElementException {
        Cart cart = user.getCart();

        if (cart == null) {
            cart = new Cart();
            cartDAO.save(cart);
            user.setCart(cart);
        }

        PageRequest pageable = PageRequest.of(page, size);
        Page<CartItem> cartItemPage = cartItemDAO.findByCart(cart, pageable);

        List<CartItem> cartItems = cartItemPage.getContent();

        GetCartOkDTO response = new GetCartOkDTO(cartItems, (int) cartItemPage.getTotalElements());

        return response;
    }

    public Optional<CartItem> getCartItemById(Integer cartItemId) {
        Optional<CartItem> cartItem = cartItemDAO.findById(cartItemId);
        return cartItem;
    }

    public List<CartItem> getCartItemListByIds(List<Integer> cartItemIds) {
        List<CartItem> cartItemList = cartItemDAO.findAllById(cartItemIds);
        return cartItemList;
    }

    @Override
    @Transactional
    public ResponseDTO updateCartAfterOrder(User user, List<CartItem> cartItemList) throws Exception {

        Cart cart = user.getCart();

        if (cart == null) {
            throw new Exception("The cart is empty");
        }

        for (CartItem cartItem : cartItemList) {
            if (!Objects.equals(cartItem.getCart().getId(), cart.getId())) {
                throw new Exception("The cart item does not belong to the user");
            }
        }

        System.err.println("ready to update cart after order");

        for (CartItem cartItem : cartItemList) {
            Book book = cartItem.getBook();
            Integer number = cartItem.getNumber();
            book.setSales(number + book.getSales());

            System.out.println("cartItem to be deleted: " + cartItem.getId());
            cartItemDAO.delete(cartItem);
            bookService.updateBook(book);
        }

        // Flush and clear the persistence context to ensure changes are committed
        return new ResponseDTO(true, "The cart has been updated");

    }
}
