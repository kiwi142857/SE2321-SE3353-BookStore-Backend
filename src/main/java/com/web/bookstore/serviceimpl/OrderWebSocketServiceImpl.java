package com.web.bookstore.serviceimpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.bookstore.dao.CartItemDAO;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.model.User;
import com.web.bookstore.service.KafkaConsumerService;
import com.web.bookstore.service.OrderWebSocketService;

@CrossOrigin(origins = "http://localhost:3000")
@Service
public class OrderWebSocketServiceImpl extends TextWebSocketHandler implements OrderWebSocketService, KafkaConsumerService {

    private final Map<Integer, WebSocketSession> userSocketMap = new ConcurrentHashMap<>();

    private final CartItemDAO cartItemDAO;

    public OrderWebSocketServiceImpl(CartItemDAO cartItemDAO) {
        this.cartItemDAO = cartItemDAO;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        User user = (User) session.getAttributes().get("user");
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
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseDTO responseDTO = objectMapper.readValue(in, ResponseDTO.class);
            Boolean isOk = responseDTO.isOk();
            String message = responseDTO.getMessage();
            String[] parts = message.split(":");
            Integer userId = Integer.valueOf(parts[0]);
            // parts[1] is the cartItemIds, such as [1, 2, 3]
            // we need to convert it to a list of integers
            System.out.println("parts[1]: " + parts[1]);
            String[] cartItemIds = parts[1].substring(1, parts[1].length() - 1).split(",");
            // System.out.println("cartItemIds: " + cartItemIds);
            // we need to delete the cart items from the cart
            List<Integer> itemIds = Arrays.stream(cartItemIds)
                    .map(String::trim) // 去掉空格
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            for (Integer itemId : itemIds) {
                cartItemDAO.deleteById(itemId);
                System.out.println("Deleted cart item: " + itemId);
            }

            // 将parts[2]的订单信息发送给用户,先组装成json格式 {"message": parts[2]}
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("message", parts[2]);
            messageMap.put("ok", isOk);

            String jsonMessage = objectMapper.writeValueAsString(messageMap);

            if (userId != null) {
                sendMessageToUser(userId, jsonMessage);
            } else {
                System.out.println("User not found for ID: " + userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
