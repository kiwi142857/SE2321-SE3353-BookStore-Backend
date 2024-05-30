package com.web.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.bookstore.service.OrderService;
import com.web.bookstore.service.UserService;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;

import com.web.bookstore.dto.PostOrderDTO;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.model.User;
import com.web.bookstore.util.SessionUtils;

import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    public final OrderService orderService;
    public final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getOrderList(@RequestParam Optional<Integer> pageSize,
            @RequestParam Optional<Integer> pageIndex) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = userService.findUserById(sessionUser.getId());
            if (pageSize.isPresent() && pageIndex.isPresent()) {
                return ResponseEntity.ok(orderService.getOrderList(pageSize.get(), pageIndex.get(), user));
            } else {
                return ResponseEntity.ok(orderService.getAllOrders(user));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> createOrder(@RequestBody PostOrderDTO postOrderDTO) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = userService.findUserById(sessionUser.getId());
            System.out.println("postOrderDTO: " + postOrderDTO.getItems().get(0));
            return ResponseEntity.ok(orderService.createOrder(postOrderDTO, user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(false, e.getMessage()));
        }
    }

}
