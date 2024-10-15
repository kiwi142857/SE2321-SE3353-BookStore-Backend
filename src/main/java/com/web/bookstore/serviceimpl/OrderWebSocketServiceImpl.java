package com.web.bookstore.serviceimpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.model.User;
import com.web.bookstore.service.AuthService;
import com.web.bookstore.service.KafkaConsumerService;
import com.web.bookstore.service.OrderWebSocketService;
import com.web.bookstore.util.SessionUtils;

@EnableWebSocketMessageBroker
@CrossOrigin(origins = "http://localhost:3000")
@Service
public class OrderWebSocketServiceImpl extends TextWebSocketHandler implements OrderWebSocketService, KafkaConsumerService {

    private final AuthService authService;
    private final Map<Integer, WebSocketSession> userSocketMap = new ConcurrentHashMap<>();

    public OrderWebSocketServiceImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        User user = SessionUtils.getUser();
        if (user != null) {
            userSocketMap.put(user.getId(), session);
            System.out.println("User " + user.getId() + " connected with session " + session.getId());
        } else {
            System.out.println("No user found in session");
        }
    }

    @Override
    public void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) {
        // Handle incoming messages
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        userSocketMap.values().remove(session);
        System.out.println("Session " + session.getId() + " closed");
    }

    public void sendMessageToUser(Integer userId, String message) {
        WebSocketSession session = userSocketMap.get(userId);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No open session found for user " + userId);
        }
    }

    @KafkaListener(topics = "order-processed", id = "order2")
    public void listenOrderToProcess(String in) {
        System.out.println("Received message from Kafka, the topic is: order-processed, the message is: " + in);
        try {
            // 解析 Kafka 消息

            ObjectMapper objectMapper = new ObjectMapper();
            ResponseDTO responseDTO = objectMapper.readValue(in, ResponseDTO.class);
            // 获取用户信息
            // Integer userId = responseDTO.getUserId();
            // if (user != null) {
            //     // 根据解析结果发送消息
            //     if (responseDTO.isOk()) {
            //         sendMessageToUser(user, "订单已完成处理: " + responseDTO.getMessage());
            //     } else {
            //         sendMessageToUser(user, "订单处理失败: " + responseDTO.getMessage());
            //     }
            // } else {
            //     System.out.println("User not found for ID: " + responseDTO.getUserId());
            // }
            Boolean isOk = responseDTO.isOk();
            String message = responseDTO.getMessage();
            // message 格式为 userId:message
            String[] parts = message.split(":");
            Integer userId = Integer.valueOf(parts[0]);
            // User user = authService.findUserById(userId);
            if (userId != null) {
                if (isOk) {
                    sendMessageToUser(userId, "订单已完成处理: " + parts[1]);
                } else {
                    sendMessageToUser(userId, "订单处理失败: " + parts[1]);
                }
            } else {
                System.out.println("User not found for ID: " + userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
