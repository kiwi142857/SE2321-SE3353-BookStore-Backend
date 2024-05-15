package com.web.bookstore.dto;

import com.web.bookstore.model.Book;

import lombok.Data;

@Data
public class BookBreifDTO {

    private Integer id;

    private String title;

    private String author;

    private Integer price;

    private String cover;

    private String tag;

    private Integer sales;

    public BookBreifDTO(Integer id, String title, String author, Integer price, String cover, String tag,
            Integer sales) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.cover = cover;
        this.tag = tag;
        this.sales = sales;
    }

    public BookBreifDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.price = book.getPrice();
        this.cover = book.getCover();
        this.tag = book.getTag();
        this.sales = book.getSales();
    }
}
