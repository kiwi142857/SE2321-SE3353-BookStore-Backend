package com.web.bookstore.repository;

import com.web.bookstore.model.Cart;
import com.web.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
