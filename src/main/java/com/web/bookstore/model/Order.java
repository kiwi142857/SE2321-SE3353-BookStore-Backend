package com.web.bookstore.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.time.Instant;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @ManyToOne
    private User user;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "address")
    private String address;

    @Column(name = "tel")
    private String tel;

    @Column(name = "createdAt")
    private Instant createdAt;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;

    public Order() {
    }

    public Order(User user, String receiver, String address, String tel, Instant createdAt) {
        this.user = user;
        this.receiver = receiver;
        this.address = address;
        this.tel = tel;
        this.createdAt = createdAt;
    }
}
