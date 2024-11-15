package com.serverless.multi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    // 从url参数中获取书籍价格与数量，相乘后返回
    @GetMapping("/price")
    public ResponseEntity<Integer> calculatePrice(@RequestParam("price") Integer price, @RequestParam("quantity") Integer quantity) {
        return ResponseEntity.ok(price * quantity);
    }

}
