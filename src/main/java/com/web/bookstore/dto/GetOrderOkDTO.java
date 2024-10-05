package com.web.bookstore.dto;

import lombok.Data;
import java.util.List;

import java.time.Instant;
import java.util.stream.Collectors;

import com.web.bookstore.model.Order;

@Data
public class GetOrderOkDTO {

    public Integer id;
    public String receiver;
    public String address;
    public String tel;
    // created At
    public Instant createdAt;
    public Integer userId;

    public List<OrderItemDTO> items;

    public GetOrderOkDTO(Order order) {
        this.id = order.getId();
        this.receiver = order.getReceiver();
        this.address = order.getAddress();
        this.tel = order.getTel();
        this.createdAt = order.getCreatedAt();
        this.items = order.getItems().stream().map(orderItem -> new OrderItemDTO(orderItem))
                .collect(Collectors.toList());
        this.userId = order.getUser().getId();
    }

    public GetOrderOkDTO() {
    }

}
