package com.web.bookstore.serviceimpl;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.bookstore.dao.CartItemDAO;
import com.web.bookstore.dao.OrderDAO;
import com.web.bookstore.dao.OrderItemDAO;
import com.web.bookstore.dto.GetOrderOkDTO;
import com.web.bookstore.dto.GetOrderOkDTOList;
import com.web.bookstore.dto.PostOrderDTO;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.model.Cart;
import com.web.bookstore.model.CartItem;
import com.web.bookstore.model.Order;
import com.web.bookstore.model.OrderItem;
import com.web.bookstore.model.User;
import com.web.bookstore.service.CartService;
import com.web.bookstore.service.OrderService;
import com.web.bookstore.service.UserService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;
    private final CartService cartService;
    private final CartItemDAO cartItemDAO;
    private final UserService userService;
    private final OrderItemDAO orderItemDAO;

    public OrderServiceImpl(OrderDAO orderDAO, CartService cartService, UserService userService, OrderItemDAO orderItemDAO, CartItemDAO cartItemDAO) {
        this.orderDAO = orderDAO;
        this.cartService = cartService;
        this.userService = userService;
        this.cartItemDAO = cartItemDAO;
        this.orderItemDAO = orderItemDAO;
    }

    @Override
    public GetOrderOkDTOList getOrderList(Integer pageSize, Integer pageNumber, User user, String startTime,
            String endTime,
            String keyWord) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));

        // Convert startTime and endTime to Instant
        Instant startInstant = startTime != null ? Instant.parse(startTime) : null;
        Instant endInstant = endTime != null ? Instant.parse(endTime) : null;

        Page<Order> orderPage = orderDAO.findByUser(user, pageable, startInstant, endInstant, keyWord);

        List<GetOrderOkDTO> getOrderOkDTOList = orderPage.stream()
                .map(order -> new GetOrderOkDTO(order))
                .collect(Collectors.toList());

        return new GetOrderOkDTOList(getOrderOkDTOList, (int) orderPage.getTotalElements());
    }

    @Override
    @Transactional
    /**
     * Note: I have remove the operation of deleting cart items from the cart,
     * to make some tests.
     */
    public ResponseDTO createOrder(PostOrderDTO postOrderDTO, User user) {
        try {
            List<CartItem> cartItemList = cartService.getCartItemListByIds(postOrderDTO.getItems());
            Cart cart = user.getCart();

            if (cartItemList.isEmpty()) {
                return new ResponseDTO(false, "The cart is empty");
            }

            // for every cart item, check if their cart is the same as the user's cart
            for (CartItem cartItem : cartItemList) {
                if (!Objects.equals(cartItem.getCart().getId(), cart.getId())) {
                    System.out.println("cartItem.getCart(): " + cartItem.getCart().getId());
                    System.out.println("cart.getId(): " + cart.getId());
                    return new ResponseDTO(false, "The cart item does not belong to the user");
                }
            }

            // for each cartitem, check whether the book is still in stock, if so update the
            // stock,else return error
            for (CartItem cartItem : cartItemList) {
                if (cartItem.getBook().getStock() < cartItem.getNumber()) {
                    throw new RuntimeException("The book is out of stock");
                } else {
                    cartItem.getBook().setStock(cartItem.getBook().getStock() - cartItem.getNumber());
                }
            }

            // for each cartitem, calculate the total price,and update the user's balance
            Integer totalPrice = 0;
            for (CartItem cartItem : cartItemList) {
                totalPrice += cartItem.getBook().getPrice() * cartItem.getNumber() * cartItem.getBook().getDiscount() / 10;
            }
            if (user.getBalance() < totalPrice) {
                throw new RuntimeException("The balance is not enough");
            }
            user.setBalance(user.getBalance() - totalPrice);
            userService.save(user);

            Order order = new Order(user, postOrderDTO.getReceiver(), postOrderDTO.getAddress(), postOrderDTO.getTel(),
                    Instant.now(), cartItemList);

            // delete all cart items、
            // System.out.println("Ready to delete cart items");
            // cartService.updateCartAfterOrder(user, cartItemList);
            // for (CartItem cartItem : cartItemList) {
            //     cartService.deleteCartItem(user, cartItem.getId());
            // }
            // for (CartItem cartItem : cartItemList) {
            //     System.out.println("Deleting cart item: " + cartItem.getId() + " in createOrder");
            //     cartItemDAO.delete(cartItem);
            // }
            // System.out.println("Cart items deleted");
            // print the order id
            System.out.println("order id: " + order.getId());
            orderDAO.save(order);
            List<OrderItem> orderItems = order.getItems();
            for (OrderItem orderItem : orderItems) {
                orderItemDAO.save(orderItem);
            }

            return new ResponseDTO(true, "The order has been created");
        } catch (Exception e) {
            // Log the exception and ensure the transaction is rolled back
            System.err.println("Error creating order: " + e.getMessage());
            // Reroll the transaction
            throw new RuntimeException(e);
        }
    }

    @Override
    public GetOrderOkDTOList getOrderListAdmin(Integer pageSize, Integer pageNumber, String startTime, String endTime,
            String keyWord) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));

        // Convert startTime and endTime to Instant
        Instant startInstant = startTime != null ? Instant.parse(startTime) : null;
        Instant endInstant = endTime != null ? Instant.parse(endTime) : null;

        Page<Order> orderPage = orderDAO.findOrders(keyWord, startInstant, endInstant, pageable);

        List<GetOrderOkDTO> getOrderOkDTOList = orderPage.stream()
                .map(order -> new GetOrderOkDTO(order))
                .collect(Collectors.toList());

        return new GetOrderOkDTOList(getOrderOkDTOList, (int) orderPage.getTotalElements());
    }
}
