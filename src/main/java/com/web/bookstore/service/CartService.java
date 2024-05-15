package com.web.bookstore.service;

import com.web.bookstore.dto.GetCartOkDTO;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.model.User;
import com.web.bookstore.model.CartItem;

import java.util.List;

public interface CartService {

    public GetCartOkDTO getCart(User user);

    public ResponseDTO addCartItem(User user, Integer bookId);

    public ResponseDTO deleteCartItem(User user, Integer cartItemId);

    public ResponseDTO updateCartItem(User user, Integer bookId, Integer quantity);

    public List<CartItem> getCartItemListByIds(List<Integer> ids);
    // public boolean checkout(Integer userId);

    // public boolean cancelCheckout(Integer userId);

    public ResponseDTO updateCartAfterOrder(User user, List<CartItem> cartItemList) throws Exception;
}
