package com.web.bookstore.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.web.bookstore.dto.PostBookDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "printYear")
    private Integer printYear;

    @Column(name = "price")
    private Integer price;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "fiveStarNumber")
    private Integer fiveStarNumber;

    @Column(name = "fourStarNumber")
    private Integer fourStarNumber;

    @Column(name = "threeStarNumber")
    private Integer threeStarNumber;

    @Column(name = "twoStarNumber")
    private Integer twoStarNumber;

    @Column(name = "oneStarNumber")
    private Integer oneStarNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    @JsonManagedReference
    private List<Comment> comments;

    @Column(name = "cover")
    private String cover;

    @Transient
    private byte[] coverContent;

    @Column(name = "sales")
    private Integer sales;

    @Column(name = "tag")
    private String tag;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    @JsonManagedReference
    private List<BookRate> bookRates;

    // 由于定长，所以使用char而不是默认的varchar
    @Column(name = "isbn", length = 13)
    private String isbn;

    // 库存
    @Column(name = "stock")
    private Integer stock;

    public Book() {
    }

    public Integer getBookRate(Integer starNumber) {
        if (null == starNumber) {
            return oneStarNumber;
        } else {
            return switch (starNumber) {
                case 5 ->
                    fiveStarNumber;
                case 4 ->
                    fourStarNumber;
                case 3 ->
                    threeStarNumber;
                case 2 ->
                    twoStarNumber;
                default ->
                    oneStarNumber;
            };
        }
    }

    public void updateRate(Integer oldStarNumber, Integer newStarNumber) {
        if (null != oldStarNumber) {
            switch (oldStarNumber) {
                case 5 ->
                    fiveStarNumber--;
                case 4 ->
                    fourStarNumber--;
                case 3 ->
                    threeStarNumber--;
                case 2 ->
                    twoStarNumber--;
                case 1 ->
                    oneStarNumber--;
                default -> {
                }
            }
        }
        if (null != newStarNumber) {
            switch (newStarNumber) {
                case 5 ->
                    fiveStarNumber++;
                case 4 ->
                    fourStarNumber++;
                case 3 ->
                    threeStarNumber++;
                case 2 ->
                    twoStarNumber++;
                case 1 ->
                    oneStarNumber++;
                default -> {
                }
            }
        }
    }

    public void addRate(Integer starNumber) {
        if (null != starNumber) {
            switch (starNumber) {
                case 5 ->
                    fiveStarNumber++;
                case 4 ->
                    fourStarNumber++;
                case 3 ->
                    threeStarNumber++;
                case 2 ->
                    twoStarNumber++;
                case 1 ->
                    oneStarNumber++;
                default -> {
                }
            }
        }
    }

    public void updateBook(PostBookDTO book) {
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
        this.tag = book.getTag();
        this.sales = book.getSales();
        this.isbn = book.getIsbn();
        this.stock = book.getStock();
        this.coverContent = book.getCoverContent();
    }

    public Book(PostBookDTO book) {
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
        this.tag = book.getTag();
        this.sales = book.getSales();
        this.isbn = book.getIsbn();
        this.stock = book.getStock();
        this.coverContent = book.getCoverContent();
    }
}
