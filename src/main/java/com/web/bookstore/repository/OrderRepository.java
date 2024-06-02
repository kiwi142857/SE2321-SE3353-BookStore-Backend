package com.web.bookstore.repository;

import com.web.bookstore.model.Order;
import com.web.bookstore.model.User;

import java.util.List;
import java.util.Optional;
import java.time.Instant;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {
    Page<Order> findByUser(User user, Pageable pageable);

    @Query("SELECT o FROM Order o JOIN o.items i JOIN i.book b WHERE o.user = :user AND (:keyWord IS NULL OR b.title LIKE CONCAT('%', :keyWord, '%')) AND (:startTime IS NULL OR o.createdAt >= :startTime) AND (:endTime IS NULL OR o.createdAt <= :endTime)")
    Page<Order> findByUser(User user, Pageable pageable, Instant startTime, Instant endTime, String keyWord);

    Optional<Order> findById(Integer id);

    Order save(Order order);

    @Query("SELECT o FROM Order o JOIN o.items i JOIN i.book b WHERE (:keyWord IS NULL OR b.title LIKE CONCAT('%', :keyWord, '%')) AND (:startTime IS NULL OR o.createdAt >= :startTime) AND (:endTime IS NULL OR o.createdAt <= :endTime)")
    Page<Order> findOrders(@Param("keyWord") String keyWord, @Param("startTime") Instant startTime,
            @Param("endTime") Instant endTime, Pageable pageable);

    List<Order> findAllByCreatedAtBetween(Instant startTime, Instant endTime);
}