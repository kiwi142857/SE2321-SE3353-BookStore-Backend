package com.web.bookstore.serviceimpl;

import com.web.bookstore.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;
import com.web.bookstore.dto.GetOrderOkDTO;

import com.web.bookstore.repository.OrderRepository;
import com.web.bookstore.repository.AuthRepository;
import com.web.bookstore.service.AuthService;
import com.web.bookstore.service.BookService;
import com.web.bookstore.model.Order;
import com.web.bookstore.model.User;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private AuthService authService;
    private BookService bookService;

    public OrderServiceImpl(OrderRepository orderRepository, AuthService authService) {
        this.orderRepository = orderRepository;
        this.authService = authService;
    }

    public List<GetOrderOkDTO> getOrderList(Integer pageSize, Integer pageNumber, String token) {
        User user = authService.getUserByToken(token);
        List<Order> orderList = orderRepository.findByUser(user);

        // 使用流操作将orderList转换为GetOrderOkDTO
        List<GetOrderOkDTO> getOrderOkDTOList = orderList.stream().map(order -> {
            GetOrderOkDTO getOrderOkDTO = new GetOrderOkDTO(order, bookService);
            return getOrderOkDTO;
        }).collect(Collectors.toList());

        return getOrderOkDTOList;
    }
}
