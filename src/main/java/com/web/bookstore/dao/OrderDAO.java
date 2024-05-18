package com.web.bookstore.dao;

import com.web.bookstore.model.Order;
import com.web.bookstore.model.User;
import com.web.bookstore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDAO {

    private final OrderRepository orderRepository;

    public OrderDAO(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }

}