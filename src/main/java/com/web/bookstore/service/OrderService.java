package com.web.bookstore.service;

import org.springframework.stereotype.Service;

import java.util.List;
import com.web.bookstore.dto.GetOrderOkDTO;
import com.web.bookstore.dto.PostOrderDTO;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.model.User;

@Service
public interface OrderService {
    public List<GetOrderOkDTO> getOrderList(Integer pageSize, Integer pageNumber, User user);

    public ResponseDTO createOrder(PostOrderDTO postOrderDTO, User user);

}
