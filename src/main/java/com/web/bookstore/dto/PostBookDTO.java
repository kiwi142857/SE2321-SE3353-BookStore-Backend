package com.web.bookstore.dto;

import java.util.List;

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
    // covercontent
    private byte[] coverContent;
    private List<String> tags;
    private Integer sales;
    private String isbn;
    private Integer stock;

    public PostBookDTO(String title, String author, String description, Integer printYear, Integer price,
            Integer discount,
            Integer fiveStarNumber, Integer fourStarNumber, Integer threeStarNumber, Integer twoStarNumber,
            String cover,
            List<String> tags, Integer sales, String isbn, Integer stock, Integer oneStarNumber, byte[] coverContent) {
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
        this.tags = tags;
        this.sales = sales;
        this.isbn = isbn;
        this.stock = stock;
        this.coverContent = coverContent;
    }

}
