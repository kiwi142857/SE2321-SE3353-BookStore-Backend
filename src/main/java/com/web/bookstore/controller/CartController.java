package com.web.bookstore.controller;

import org.apache.catalina.connector.Response;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;

import com.web.bookstore.service.AuthService;
import com.web.bookstore.service.CartService;
import com.web.bookstore.model.User;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final AuthService authService;

    public CartController(CartService cartService, AuthService authService) {
        this.cartService = cartService;
        this.authService = authService;
    }

    @PutMapping("")
    public ResponseEntity<Object> addCartItem(@RequestParam Integer bookId,
            @CookieValue(value = "token") String token) {
        try {
            User user = authService.getUserByToken(token);
            if (user == null) {
                return ResponseEntity.status(Response.SC_UNAUTHORIZED).body("Please login first");
            }
            return ResponseEntity.ok(cartService.addCartItem(user, bookId));
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<Object> getCart(@CookieValue(value = "token") String token) {
        try {
            User user = authService.getUserByToken(token);
            if (user == null) {
                return ResponseEntity.status(Response.SC_UNAUTHORIZED).body("Please login first");
            }
            return ResponseEntity.ok(cartService.getCart(user));
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
