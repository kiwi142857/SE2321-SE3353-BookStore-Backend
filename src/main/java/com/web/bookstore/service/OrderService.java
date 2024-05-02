package com.web.bookstore.service;

import org.springframework.stereotype.Service;

import java.util.List;
import com.web.bookstore.dto.GetOrderOkDTO;

@Service
public interface OrderService {
    public List<GetOrderOkDTO> getOrderList(Integer pageSize, Integer pageNumber, String token);
}
