package com.web.bookstore.service;

public interface KafkaProducerService {

    public <T> void sendMessage(String topic, T message);
}
