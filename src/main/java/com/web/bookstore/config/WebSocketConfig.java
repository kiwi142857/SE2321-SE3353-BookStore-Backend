package com.web.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.web.bookstore.serviceimpl.OrderWebSocketServiceImpl;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final OrderWebSocketServiceImpl orderWebSocketServiceImpl;

    public WebSocketConfig(OrderWebSocketServiceImpl orderWebSocketServiceImpl) {
        this.orderWebSocketServiceImpl = orderWebSocketServiceImpl;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(orderWebSocketServiceImpl, "/order/websocket")
                .addInterceptors(new CustomHttpSessionHandshakeInterceptor())
                .setAllowedOriginPatterns("*");
    }
}
