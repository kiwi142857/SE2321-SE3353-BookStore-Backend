package com.web.bookstore.service;

import com.web.bookstore.dto.GetCartOkDTO;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.model.User;

public interface CartService {

    public GetCartOkDTO getCart(User user);

    public ResponseDTO addCartItem(User user, Integer bookId);

    public ResponseDTO deleteCartItem(User user, Integer bookId);

    public ResponseDTO updateCartItem(User user, Integer bookId, Integer quantity);

    // public boolean checkout(Integer userId);

    // public boolean cancelCheckout(Integer userId);
}
