package com.web.bookstore.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.web.bookstore.service.OrderCalculationService;

@Service
public class OrderCalculationServiceImpl implements OrderCalculationService {

    @Autowired
    private static RestTemplate restTemplate;

    public static Integer calculateTotalPrice(Integer price, int quantity) {
        // 定义函数式服务的 URI
        String url = "http://localhost:8081/api/orders/price?price={price}&quantity={quantity}";
        // 通过调用函数式服务获取每个 CartItem 的总价
        return restTemplate.getForObject(url, Integer.class, price, quantity);
    }
}
