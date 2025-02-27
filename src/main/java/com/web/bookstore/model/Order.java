package com.web.bookstore.model;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
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

    public Order(User user, String receiver, String address, String tel, Instant createdAt, List<CartItem> cartItems) {
        this.user = user;
        this.receiver = receiver;
        this.address = address;
        this.tel = tel;
        this.createdAt = createdAt;
        this.items = cartItems.stream().map(cartItem -> new OrderItem(cartItem, this)).collect(Collectors.toList());
    }
}
