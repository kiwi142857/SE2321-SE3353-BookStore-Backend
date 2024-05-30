package com.web.bookstore.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.List;

import org.apache.ibatis.annotations.One;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @Column(name = "sales")
    private Integer sales;

    @Column(name = "tag")
    private String tag;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    @JsonManagedReference
    private List<BookRate> bookRates;

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
        if (oldStarNumber == 5) {
            fiveStarNumber--;
        } else if (oldStarNumber == 4) {
            fourStarNumber--;
        } else if (oldStarNumber == 3) {
            threeStarNumber--;
        } else if (oldStarNumber == 2) {
            twoStarNumber--;
        } else if (oldStarNumber == 1) {
            oneStarNumber--;
        }
        if (newStarNumber == 5) {
            fiveStarNumber++;
        } else if (newStarNumber == 4) {
            fourStarNumber++;
        } else if (newStarNumber == 3) {
            threeStarNumber++;
        } else if (newStarNumber == 2) {
            twoStarNumber++;
        } else if (newStarNumber == 1) {
            oneStarNumber++;
        }
    }

    public void addRate(Integer starNumber) {
        if (starNumber == 5) {
            fiveStarNumber++;
        } else if (starNumber == 4) {
            fourStarNumber++;
        } else if (starNumber == 3) {
            threeStarNumber++;
        } else if (starNumber == 2) {
            twoStarNumber++;
        } else if (starNumber == 1) {
            oneStarNumber++;
        }
    }

}
