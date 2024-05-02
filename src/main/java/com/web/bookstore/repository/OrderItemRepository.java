package com.web.bookstore.repository;

import com.web.bookstore.model.OrderItem;
import com.web.bookstore.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrderId(Integer orderId);

    List<OrderItem> findByOder(Order order);
}
