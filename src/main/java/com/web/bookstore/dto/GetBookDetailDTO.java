package com.web.bookstore.dto;

import lombok.Data;

import com.web.bookstore.model.Book;

@Data
public class GetBookDetailDTO {

    private Integer id;
    private String title;
    private String author;
    private String description;
    private String isbn;
    private Integer printYear;
    private Integer price;
    private Integer discount;
    private Integer fiveStarNumber;
    private Integer fourStarNumber;
    private Integer threeStarNumber;
    private Integer twoStarNumber;
    private Integer oneStarNumber;
    private String cover;
    private Integer sales;
    private String tag;

    public GetBookDetailDTO(Integer id, String title, String author, String description, Integer printYear,
            Integer price, Integer discount, Integer fiveStarNumber, Integer fourStarNumber, Integer threeStarNumber,
            Integer twoStarNumber, Integer oneStarNumber, String cover, Integer sales, String tag) {
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
    }

    public GetBookDetailDTO(Book book) {
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
        this.cover = book.getCover();
        this.sales = book.getSales();
        this.tag = book.getTag();
    }

    public GetBookDetailDTO(GetBookDetailDTO book) {
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
        this.cover = book.getCover();
        this.sales = book.getSales();
        this.tag = book.getTag();
    }
}
