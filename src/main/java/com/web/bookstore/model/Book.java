package com.web.bookstore.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.web.bookstore.dto.BookJsonDTO;
import com.web.bookstore.dto.PostBookDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "book")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book", fetch = FetchType.EAGER)
    @JsonManagedReference("commentReference")
    private List<Comment> comments;

    @Column(name = "cover")
    private String cover;

    @Column(name = "sales")
    private Integer sales;

    @Column(name = "tag")
    private String tag;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book", fetch = FetchType.EAGER)
    @JsonManagedReference("bookRateReference")
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
        if (starNumber == 5) {
            return fiveStarNumber;
        } else if (starNumber == 4) {
            return fourStarNumber;
        } else if (starNumber == 3) {
            return threeStarNumber;
        } else if (starNumber == 2) {
            return twoStarNumber;
        } else {
            return oneStarNumber;
        }
    }

    public void updateRate(Integer oldStarNumber, Integer newStarNumber) {
        if (null != oldStarNumber) {
            switch (oldStarNumber) {
                case 5:
                    fiveStarNumber--;
                    break;
                case 4:
                    fourStarNumber--;
                    break;
                case 3:
                    threeStarNumber--;
                    break;
                case 2:
                    twoStarNumber--;
                    break;
                case 1:
                    oneStarNumber--;
                    break;
                default:
                    break;
            }
        }
        if (null != newStarNumber) {
            switch (newStarNumber) {
                case 5:
                    fiveStarNumber++;
                    break;
                case 4:
                    fourStarNumber++;
                    break;
                case 3:
                    threeStarNumber++;
                    break;
                case 2:
                    twoStarNumber++;
                    break;
                case 1:
                    oneStarNumber++;
                    break;
                default:
                    break;
            }
        }
    }

    public void addRate(Integer starNumber) {
        if (null != starNumber) {
            switch (starNumber) {
                case 5:
                    fiveStarNumber++;
                    break;
                case 4:
                    fourStarNumber++;
                    break;
                case 3:
                    threeStarNumber++;
                    break;
                case 2:
                    twoStarNumber++;
                    break;
                case 1:
                    oneStarNumber++;
                    break;
                default:
                    break;
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
    }

    public Book(BookJsonDTO dto) {
        this.id = dto.getId();
        this.title = dto.getTitle();
        this.author = dto.getAuthor();
        this.description = dto.getDescription();
        this.printYear = dto.getPrintYear();
        this.price = dto.getPrice();
        this.discount = dto.getDiscount();
        this.fiveStarNumber = dto.getFiveStarNumber();
        this.fourStarNumber = dto.getFourStarNumber();
        this.threeStarNumber = dto.getThreeStarNumber();
        this.twoStarNumber = dto.getTwoStarNumber();
        this.oneStarNumber = dto.getOneStarNumber();
        this.comments = dto.getComments() != null ? dto.getComments() : new ArrayList<>();
        this.cover = dto.getCover();
        this.sales = dto.getSales();
        this.tag = dto.getTag();
        this.isbn = dto.getIsbn();
        this.stock = dto.getStock();
        this.bookRates = dto.getBookRateDTOs().stream().map(BookRate::new).toList();
    }

}
