package com.web.bookstore.dto;

import lombok.Data;

@Data
public class PostBookDTO {

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
    private String cover;
    private String tag;
    private Integer sales;
    private String isbn;
    private Integer stock;

    public PostBookDTO(String title, String author, String description, Integer printYear, Integer price,
            Integer discount,
            Integer fiveStarNumber, Integer fourStarNumber, Integer threeStarNumber, Integer twoStarNumber,
            String cover,
            String tag, Integer sales, String isbn, Integer stock, Integer oneStarNumber) {
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
        this.tag = tag;
        this.sales = sales;
        this.isbn = isbn;
        this.stock = stock;
    }

}
