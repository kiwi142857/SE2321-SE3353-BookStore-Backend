package com.web.bookstore.serviceimpl;

import com.web.bookstore.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;
import com.web.bookstore.dto.GetOrderOkDTO;
import com.web.bookstore.dto.PostOrderDTO;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.repository.OrderRepository;
import com.web.bookstore.repository.AuthRepository;
import com.web.bookstore.service.AuthService;
import com.web.bookstore.service.BookService;
import com.web.bookstore.model.CartItem;
import com.web.bookstore.model.Order;
import com.web.bookstore.model.User;
import com.web.bookstore.model.Cart;
import com.web.bookstore.service.CartService;
import com.web.bookstore.dao.OrderDAO;

import java.util.Comparator;
import java.time.Instant;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;
    private CartService cartService;

    public OrderServiceImpl(OrderDAO orderDAO, CartService cartService) {
        this.orderDAO = orderDAO;
        this.cartService = cartService;
    }

    public List<GetOrderOkDTO> getOrderList(Integer pageSize, Integer pageNumber, User user) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Order> orderPage = orderDAO.findByUser(user, pageable);

        List<GetOrderOkDTO> getOrderOkDTOList = orderPage.stream()
                .map(order -> new GetOrderOkDTO(order))
                .collect(Collectors.toList());

        return getOrderOkDTOList;
    }

    public ResponseDTO createOrder(PostOrderDTO postOrderDTO, User user) {

        List<CartItem> cartItemList = cartService.getCartItemListByIds(postOrderDTO.getItems());
        Cart cart = user.getCart();

        if (cartItemList.size() == 0) {
            return new ResponseDTO(false, "The cart is empty");
        }

        // for every cart item, check if their cart is the same as the user's cart
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getCart() != cart) {
                return new ResponseDTO(false, "The cart item does not belong to the user");
            }
        }

        Order order = new Order(user, postOrderDTO.getReceiver(), postOrderDTO.getAddress(), postOrderDTO.getTel(),
                Instant.now(), cartItemList);

        orderDAO.save(order);

        // delete all cart items
        try {
            cartService.updateCartAfterOrder(user,
                    cartItemList);
        } catch (Exception e) {
            return new ResponseDTO(false, e.getMessage());
        }

        return new ResponseDTO(true, "The order has been created");
    }
}
