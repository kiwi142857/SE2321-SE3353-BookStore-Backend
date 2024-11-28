package com.web.bookstore.dto;

import java.util.List;

import com.web.bookstore.model.Book;

import lombok.Data;

@Data
public class BookBreifDTO {

    private Integer id;

    private String title;

    private String author;

    private Integer price;

    private String cover;

    private List<String> tags;

    private Integer sales;

    private Integer discount;

    private byte[] coverContent;

    // ISBN
    private String isbn;

    // stock
    private Integer stock;

    // 表示在某个时间范围内的销量
    private Long salesInTime;

    public BookBreifDTO(Integer id, String title, String author, Integer price, String cover, List<String> tags,
            Integer sales, Integer discount, String isbn, Integer stock, Long salesInTime, byte[] coverContent) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.cover = cover;
        this.tags = tags;
        this.sales = sales;
        this.discount = discount;
        this.isbn = isbn;
        this.stock = stock;
        this.salesInTime = salesInTime;
        this.coverContent = coverContent;
    }

    public BookBreifDTO(Integer id, String title, String author, Integer price, String cover, List<String> tags,
            Integer sales, Integer discount, String isbn, Integer stock, byte[] coverContent) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.cover = cover;
        this.tags = tags;
        this.sales = sales;
        this.discount = discount;
        this.isbn = isbn;
        this.stock = stock;
        this.salesInTime = 0L;
        this.coverContent = coverContent;
    }

    public BookBreifDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.price = book.getPrice();
        this.cover = book.getCover();
        this.tags = book.getTags().stream().map(tag -> tag.getName()).toList();
        this.sales = book.getSales();
        this.discount = book.getDiscount();
        this.isbn = book.getIsbn();
        this.stock = book.getStock();
        this.salesInTime = 0L;
        this.coverContent = book.getCoverContent();
    }

    public BookBreifDTO(Book book, Long salesInTime) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.price = book.getPrice();
        this.cover = book.getCover();
        this.tags = book.getTags().stream().map(tag -> tag.getName()).toList();
        this.sales = book.getSales();
        this.discount = book.getDiscount();
        this.isbn = book.getIsbn();
        this.stock = book.getStock();
        this.coverContent = book.getCoverContent();
        this.salesInTime = salesInTime;
    }
}
