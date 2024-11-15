package com.web.bookstore.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.web.bookstore.service.OrderCalculationService;

@Service
public class OrderCalculationServiceImpl implements OrderCalculationService {

    @Autowired
    private RestTemplate restTemplate;

    public Integer calculateTotalPrice(Integer price, int quantity) {
        System.out.println("price: " + price + " quantity: " + quantity);
        // 定义函数式服务的 URI
        String url = "http://localhost:8081/api/orders/price?price={price}&quantity={quantity}";
        // 通过调用函数式服务获取每个 CartItem 的总价
        String response = restTemplate.getForObject(url, String.class, price, quantity);
        System.out.println("The total price is: " + response);
        return Integer.parseInt(response);
    }
}
