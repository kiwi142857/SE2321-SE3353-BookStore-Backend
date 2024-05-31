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
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {
    Page<Order> findByUser(User user, Pageable pageable);

    Optional<Order> findById(Integer id);

    Order save(Order order);
}