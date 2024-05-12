package com.web.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.bookstore.service.OrderService;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;

import com.web.bookstore.dto.PostOrderDTO;
import com.web.bookstore.dto.ResponseDTO;

import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    public final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getOrderList(@RequestParam Optional<Integer> pageSize,
            @RequestParam Optional<Integer> pageIndex,
            @CookieValue(value = "token") String token) {
        try {
            System.out.println("pageSize: " + pageSize.orElse(null));
            System.out.println("pageIndex: " + pageIndex.orElse(null));
            System.out.println("token: " + token);
            if (pageSize.isPresent() && pageIndex.isPresent()) {
                return ResponseEntity.ok(orderService.getOrderList(pageSize.get(), pageIndex.get(), token));
            } else {
                return ResponseEntity.ok(orderService.getAllOrders(token));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> createOrder(@RequestBody PostOrderDTO postOrderDTO,
            @CookieValue(value = "token") String token) {
        try {
            System.out.println("postOrderDTO: " + postOrderDTO.getItems().get(0));
            return ResponseEntity.ok(orderService.createOrder(postOrderDTO, token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, e.getMessage()));
        }
    }

}
