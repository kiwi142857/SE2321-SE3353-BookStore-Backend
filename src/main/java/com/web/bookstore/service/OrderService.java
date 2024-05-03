package com.web.bookstore.service;

import org.springframework.stereotype.Service;

import java.util.List;
import com.web.bookstore.dto.GetOrderOkDTO;
import com.web.bookstore.dto.PostOrderDTO;
import com.web.bookstore.dto.ResponseDTO;

@Service
public interface OrderService {
    public List<GetOrderOkDTO> getOrderList(Integer pageSize, Integer pageNumber, String token);

    public ResponseDTO createOrder(PostOrderDTO postOrderDTO, String token);

    public List<GetOrderOkDTO> getAllOrders(String token);
}
