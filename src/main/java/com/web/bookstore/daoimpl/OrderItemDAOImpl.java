package com.web.bookstore.daoimpl;

import com.web.bookstore.model.Order;
import com.web.bookstore.model.OrderItem;
import com.web.bookstore.dao.OrderItemDAO;
import com.web.bookstore.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemDAOImpl implements OrderItemDAO {

    private final OrderItemRepository orderItemRepository;

    public OrderItemDAOImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> findByOrderId(Integer orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    public List<OrderItem> findByOrder(Order order) {
        return orderItemRepository.findByOrder(order);
    }

    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
