package com.web.bookstore.repository;

import com.web.bookstore.model.Order;
import com.web.bookstore.model.OrderItem;
import com.web.bookstore.dto.OrderItemDTO;
import com.web.bookstore.dto.GetOrderOkDTO;
import com.web.bookstore.dto.GetBookDetailDTO;
import com.web.bookstore.repository.OrderItemRepository;
import com.web.bookstore.model.User;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUser(User user);

}
