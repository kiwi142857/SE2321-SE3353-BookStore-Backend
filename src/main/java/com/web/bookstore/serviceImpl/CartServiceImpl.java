package com.web.bookstore.serviceimpl;

import com.web.bookstore.dto.GetCartOkDTO;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.service.CartService;
import org.springframework.stereotype.Service;
import com.web.bookstore.service.BookService;
import com.web.bookstore.model.CartItem;
import com.web.bookstore.repository.CartItemRepository;
import com.web.bookstore.repository.CartRepository;
import com.web.bookstore.dto.GetCartOkDTO;

import com.web.bookstore.model.User;
import com.web.bookstore.model.Cart;
import com.web.bookstore.model.Book;
import com.web.bookstore.model.CartItem;
import com.web.bookstore.repository.CartItemRepository;
import com.web.bookstore.repository.CartRepository;
import com.web.bookstore.dao.CartItemDAO;
import com.web.bookstore.dao.CartDAO;
import com.web.bookstore.dao.UserDAO;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

@Service
public class CartServiceImpl implements CartService {

    private final CartDAO cartDAO;
    private final CartItemDAO cartItemDAO;
    private final BookService bookService;
    private final UserDAO userDAO;

    public CartServiceImpl(CartDAO cartDAO, CartItemDAO cartItemDAO, BookService bookService, UserDAO userDAO) {
        this.cartDAO = cartDAO;
        this.cartItemDAO = cartItemDAO;
        this.bookService = bookService;
        this.userDAO = userDAO;
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

    public ResponseDTO deleteCartItem(User user, Integer cartItemId) {

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

        cartItemDAO.delete(cartItem.get());
        return new ResponseDTO(true, "The book has been removed from the cart");

    }

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

    public ResponseDTO updateCartAfterOrder(User user, List<CartItem> cartItemList) throws Exception {

        Cart cart = user.getCart();

        if (cart == null) {
            throw new Exception("The cart is empty");
        }

        for (CartItem cartItem : cartItemList) {
            if (cartItem.getCart() != cart) {
                throw new Exception("The cart item does not belong to the user");
            }
        }

        for (CartItem cartItem : cartItemList) {
            Book book = cartItem.getBook();
            Integer number = cartItem.getNumber();
            book.setSales(number + book.getSales());
            bookService.updateBook(book);
            cartItemDAO.delete(cartItem);
        }

        return new ResponseDTO(true, "The cart has been updated");

    }
}
