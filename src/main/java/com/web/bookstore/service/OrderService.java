package com.web.bookstore.service;

import org.springframework.stereotype.Service;

import java.util.List;
import com.web.bookstore.dto.GetOrderOkDTO;
import com.web.bookstore.dto.GetOrderOkDTOList;
import com.web.bookstore.dto.PostOrderDTO;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.model.User;

@Service
public interface OrderService {
    public GetOrderOkDTOList getOrderList(Integer pageSize, Integer pageIndex, User user);

    public ResponseDTO createOrder(PostOrderDTO postOrderDTO, User user);

    public GetOrderOkDTOList getOrderListAdmin(Integer pageSize, Integer pageIndex, String startTime, String endTime,
            String keyWord);
}
