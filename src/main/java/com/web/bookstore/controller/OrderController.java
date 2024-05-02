package com.web.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.bookstore.service.OrderService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import com.web.bookstore.dto.ResponseDTO;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    public final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getOrderList(@RequestParam Integer pageSize, @RequestParam Integer pageIndex,
            @Param("token") String token) {
        try {
            return ResponseEntity.ok(orderService.getOrderList(pageSize, pageIndex, token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, e.getMessage()));
        }
    }
}
