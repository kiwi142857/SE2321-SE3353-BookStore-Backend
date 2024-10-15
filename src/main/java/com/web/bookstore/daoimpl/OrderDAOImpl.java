package com.web.bookstore.daoimpl;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.bookstore.dao.OrderDAO;
import com.web.bookstore.model.Order;
import com.web.bookstore.model.User;
import com.web.bookstore.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderDAOImpl implements OrderDAO {

    private final OrderRepository orderRepository;

    public OrderDAOImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<Order> findByUser(User user, Pageable pageable) {
        return orderRepository.findByUser(user, pageable);
    }

    @Override
    public Page<Order> findByUser(User user, Pageable pageable, Instant startTime, Instant endTime, String keyWord) {
        return orderRepository.findByUser(user, pageable, startTime, endTime, keyWord);
    }

    @Transactional
    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Page<Order> findOrders(String keyWord, Instant startTime, Instant endTime, Pageable pageable) {
        return orderRepository.findOrders(keyWord, startTime, endTime, pageable);
    }

    @Override
    public List<Order> findAllByCreatedAtBetween(Instant startTime, Instant endTime) {
        return orderRepository.findAllByCreatedAtBetween(startTime, endTime);
    }
}
