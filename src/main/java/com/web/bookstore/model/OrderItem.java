package com.web.bookstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import lombok.Data;

import com.web.bookstore.model.Book;

@Entity
@Data
@Table(name = "order_item")
public class OrderItem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "number")
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem() {
    }

    public OrderItem(Book book, Integer number, Order order) {
        this.book = book;
        this.number = number;
        this.order = order;
    }

    public OrderItem(CartItem cartItem, Order order) {
        this.book = cartItem.getBook();
        this.number = cartItem.getNumber();
        this.order = order;
    }
}
