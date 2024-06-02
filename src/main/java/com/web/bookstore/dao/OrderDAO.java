package com.web.bookstore.dao;

import com.web.bookstore.model.Order;
import com.web.bookstore.model.User;
import com.web.bookstore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class OrderDAO {

    private final OrderRepository orderRepository;

    public OrderDAO(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Page<Order> findByUser(User user, Pageable pageable) {
        return orderRepository.findByUser(user, pageable);
    }

    public Page<Order> findByUser(User user, Pageable pageable, Instant startTime, Instant endTime, String keyWord) {
        return orderRepository.findByUser(user, pageable, startTime, endTime, keyWord);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public Page<Order> findOrders(String keyWord, Instant startTime, Instant endTime, Pageable pageable) {
        return orderRepository.findOrders(keyWord, startTime, endTime, pageable);
    }

    public List<Order> findAllByCreatedAtBetween(Instant startTime, Instant endTime) {
        return orderRepository.findAllByCreatedAtBetween(startTime, endTime);
    }
}