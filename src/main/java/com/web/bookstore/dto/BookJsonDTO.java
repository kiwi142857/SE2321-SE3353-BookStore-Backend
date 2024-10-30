package com.web.bookstore.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.web.bookstore.model.Book;
import com.web.bookstore.model.Comment;

import lombok.Data;

@Data
public class BookJsonDTO {

    private Integer id;
    private String title;
    private String author;
    private String description;
    private Integer printYear;
    private Integer price;
    private Integer discount;
    private Integer fiveStarNumber;
    private Integer fourStarNumber;
    private Integer threeStarNumber;
    private Integer twoStarNumber;
    private Integer oneStarNumber;
    private List<Comment> comments;
    private String cover;
    private Integer sales;
    private String tag;
    private List<BookRateJsonDTO> bookRateDTOs;
    private String isbn;
    private Integer stock;

    // 构造函数
    public BookJsonDTO(Integer id, String title, String author, String description, Integer printYear, Integer price, Integer discount,
            Integer fiveStarNumber, Integer fourStarNumber, Integer threeStarNumber, Integer twoStarNumber, Integer oneStarNumber,
            List<Comment> comments, String cover, Integer sales, String tag, List<BookRateJsonDTO> bookRates, String isbn, Integer stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.printYear = printYear;
        this.price = price;
        this.discount = discount;
        this.fiveStarNumber = fiveStarNumber;
        this.fourStarNumber = fourStarNumber;
        this.threeStarNumber = threeStarNumber;
        this.twoStarNumber = twoStarNumber;
        this.oneStarNumber = oneStarNumber;
        this.cover = cover;
        this.sales = sales;
        this.tag = tag;
        this.bookRateDTOs = bookRates;
        this.isbn = isbn;
        this.stock = stock;
    }

    public BookJsonDTO() {
    }

    public BookJsonDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.description = book.getDescription();
        this.printYear = book.getPrintYear();
        this.price = book.getPrice();
        this.discount = book.getDiscount();
        this.fiveStarNumber = book.getFiveStarNumber();
        this.fourStarNumber = book.getFourStarNumber();
        this.threeStarNumber = book.getThreeStarNumber();
        this.twoStarNumber = book.getTwoStarNumber();
        this.oneStarNumber = book.getOneStarNumber();
        this.comments = book.getComments() != null ? book.getComments().stream().collect(Collectors.toList()) : new ArrayList<>();
        this.cover = book.getCover();
        this.sales = book.getSales();
        this.tag = book.getTag();
        this.isbn = book.getIsbn();
        this.stock = book.getStock();
        this.bookRateDTOs = book.getBookRates().stream().map(BookRateJsonDTO::new).toList();
    }
}
