package com.web.bookstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "order_item")
public class OrderItem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "book")
    private String bookTitle;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "book_price")
    private Integer bookPrice;

    @Column(name = "book_cover")
    private String bookCover;

    @Column(name = "book_discount")
    private Integer bookDiscount;

    @Column(name = "number")
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem() {
    }

    public OrderItem(Book book, Integer number, Order order) {
        this.bookTitle = book.getTitle();
        this.bookId = book.getId();
        this.bookPrice = book.getPrice();
        this.bookCover = book.getCover();
        this.bookDiscount = book.getDiscount();

        this.number = number;
        this.order = order;
    }

    public OrderItem(CartItem cartItem, Order order) {
        this.bookTitle = cartItem.getBook().getTitle();
        this.bookId = cartItem.getBook().getId();
        this.number = cartItem.getNumber();
        this.bookPrice = cartItem.getBook().getPrice();
        this.bookCover = cartItem.getBook().getCover();
        this.bookDiscount = cartItem.getBook().getDiscount();
        this.order = order;
    }
}
