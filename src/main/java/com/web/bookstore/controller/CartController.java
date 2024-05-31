package com.web.bookstore.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;

import com.web.bookstore.service.AuthService;
import com.web.bookstore.service.CartService;
import com.web.bookstore.service.UserService;
import com.web.bookstore.util.SessionUtils;
import com.web.bookstore.model.User;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @PutMapping("")
    public ResponseEntity<Object> addCartItem(@RequestParam Integer bookId) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = userService.findUserById(sessionUser.getId());
            return ResponseEntity.ok(cartService.addCartItem(user, bookId));
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // 增加分页处理
    @GetMapping("")
    public ResponseEntity<Object> getCart(@RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = userService.findUserById(sessionUser.getId());
            return ResponseEntity.ok(cartService.getCart(user, page, size));
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCartItem(@PathVariable Integer id) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = userService.findUserById(sessionUser.getId());
            return ResponseEntity.ok(cartService.deleteCartItem(user, id));
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCartItem(@PathVariable Integer id, @RequestParam Integer number) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = userService.findUserById(sessionUser.getId());
            return ResponseEntity.ok(cartService.updateCartItem(user, id, number));
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
