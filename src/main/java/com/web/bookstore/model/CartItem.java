package com.web.bookstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity
@Data
@Table(name = "cart_item")
public class CartItem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "number")
    private Integer number;

    public CartItem() {
    }

    public CartItem(Cart cart, Book book) {
        this.cart = cart;
        this.book = book;
        this.number = 1;
    }

    public void increaseNumber() {
        this.number++;
    }

    public void decreaseNumber() {
        this.number--;
    }

}
