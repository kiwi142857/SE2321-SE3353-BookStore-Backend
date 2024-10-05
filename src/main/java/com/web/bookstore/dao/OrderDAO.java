package com.web.bookstore.dao;

import com.web.bookstore.model.Order;
import com.web.bookstore.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

public interface OrderDAO {

    Page<Order> findByUser(User user, Pageable pageable);

    Page<Order> findByUser(User user, Pageable pageable, Instant startTime, Instant endTime, String keyWord);

    void save(Order order);

    Page<Order> findOrders(String keyWord, Instant startTime, Instant endTime, Pageable pageable);

    List<Order> findAllByCreatedAtBetween(Instant startTime, Instant endTime);
}