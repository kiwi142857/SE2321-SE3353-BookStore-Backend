package com.web.bookstore.dao;

import com.web.bookstore.model.Order;
import com.web.bookstore.model.OrderItem;

import java.util.List;

public interface OrderItemDAO {

    List<OrderItem> findByOrderId(Integer orderId);

    List<OrderItem> findByOrder(Order order);

    void save(OrderItem orderItem);
}