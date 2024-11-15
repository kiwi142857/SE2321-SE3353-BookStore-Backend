package com.web.microserivce.book.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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

}
