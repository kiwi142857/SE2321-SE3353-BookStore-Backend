package com.web.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.bookstore.model.Order;
import com.web.bookstore.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrderId(Integer orderId);

    List<OrderItem> findByOrder(Order order);

    List<OrderItem> findAllByBookId(Integer bookId);

    @SuppressWarnings("unchecked")
    @Override
    OrderItem save(OrderItem orderItem);
}
