package com.web.bookstore.dao;

import java.util.List;

import com.web.bookstore.model.Order;
import com.web.bookstore.model.OrderItem;

public interface OrderItemDAO {

    List<OrderItem> findByOrderId(Integer orderId);

    List<OrderItem> findByOrder(Order order);

    void save(OrderItem orderItem);
}
