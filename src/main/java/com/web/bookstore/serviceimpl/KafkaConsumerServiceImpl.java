package com.web.bookstore.serviceimpl;

import java.util.stream.Collectors;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.bookstore.dto.KafkaPostOrderDTO;
import com.web.bookstore.dto.PostOrderDTO;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.exception.UserBannedException;
import com.web.bookstore.model.User;
import com.web.bookstore.service.KafkaConsumerService;
import com.web.bookstore.service.OrderService;
import com.web.bookstore.service.UserService;

@Service
public final class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final OrderService orderService;
    private final UserService userService;
    private final KafkaProducerServiceImpl kafkaProducerService;

    public KafkaConsumerServiceImpl(OrderService orderService, UserService userService, KafkaProducerServiceImpl kafkaProducerService) {
        this.orderService = orderService;
        this.userService = userService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @KafkaListener(topics = "order-to-process", id = "order1")
    public void listenOrderToProcess(String in) {
        System.out.println("Received message from Kafka, the topic is: order-to-process, the message is: " + in);
        KafkaPostOrderDTO postOrderDTO = null;
        try {
            postOrderDTO = new ObjectMapper().readValue(in, KafkaPostOrderDTO.class);
        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }

        System.out.println("Processing message...");
        ResponseDTO responseDTO = createOrder(postOrderDTO);
        if (responseDTO.isOk() == true) {
            System.out.println("Order created successfully." + " The message is " + responseDTO.getMessage());
        } else {
            System.out.println("Order creation failed.");
        }
        kafkaProducerService.sendMessage("order-processed", responseDTO);
        System.out.println("Message processed.");
    }

    public ResponseDTO createOrder(KafkaPostOrderDTO postOrderDTO) {
        try {
            String userId = postOrderDTO.getUserId();
            System.out.println("userId: " + userId);
            if (userId == null) {
                return new ResponseDTO(false, "User not logged in");
            }
            User user = userService.findUserById(Integer.valueOf(userId));
            PostOrderDTO postOrderDTO2 = new PostOrderDTO();
            postOrderDTO2.setReceiver(postOrderDTO.getReceiver());
            postOrderDTO2.setAddress(postOrderDTO.getAddress());
            postOrderDTO2.setTel(postOrderDTO.getTel());
            postOrderDTO2.setItems(postOrderDTO.getItems());

            if (user.getStatus() == 1) {
                throw new UserBannedException("您的账号已被禁用");
            }
            System.out.println("postOrderDTO: " + postOrderDTO.getItems().get(0));
            ResponseDTO res = orderService.createOrder(postOrderDTO2, user);
            System.out.println("res: " + res);

            String itemIds = postOrderDTO.getItems().stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
            String message = userId + ":[" + itemIds + "]:" + res.getMessage();
            if (!res.isOk()) {
                return new ResponseDTO(false, message);
            }

            return new ResponseDTO(true, message);
        } catch (UserBannedException e) {
            return new ResponseDTO(false, e.getMessage());
        } catch (NumberFormatException e) {
            return new ResponseDTO(false, "User not logged in");
        } catch (Exception e) {
            return new ResponseDTO(false, e.getMessage());
        }
    }
}
