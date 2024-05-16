package com.web.bookstore.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "comment")
public class Comment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "reply")
    private String reply;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", reply='" + reply + '\'' +
                '}';
    }

    public Comment() {
    }

    public Comment(String content, Date date, Book book, User user, String reply) {
        this.content = content;
        this.date = date;
        this.book = book;
        this.user = user;
        this.reply = reply;
    }

    public Comment(String content, Book book, User user) {
        this.content = content;
        this.date = new Date();
        this.book = book;
        this.user = user;
    }

    public Comment(String content, Book book, User user, String reply) {
        this.content = content;
        this.date = new Date();
        this.book = book;
        this.user = user;
        this.reply = reply;
    }
}
