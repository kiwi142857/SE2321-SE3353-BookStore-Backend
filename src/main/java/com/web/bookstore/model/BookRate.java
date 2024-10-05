package com.web.bookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import lombok.Data;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Table;

@Entity
@Data
@Table(name = "book_rate")
public class BookRate {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @JsonBackReference
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    private Integer rate;

    public BookRate() {
    }

    public BookRate(User user, Book book, Integer rate) {
        this.user = user;
        this.book = book;
        this.rate = rate;
    }
}
