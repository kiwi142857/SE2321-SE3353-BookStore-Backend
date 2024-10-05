package com.web.bookstore.serviceimpl;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.bookstore.service.KafkaProducerService;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private Producer<String, String> producer;

    public KafkaProducerServiceImpl() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092"); // Kafka服务器地址
        props.put("key.serializer", StringSerializer.class.getName()); // 键序列化器
        props.put("value.serializer", StringSerializer.class.getName()); // 值序列化器
        props.setProperty("transactional.id", "bookstore-id"); // 事务ID

        producer = new KafkaProducer<>(props);
        producer.initTransactions();
    }

    @Override
    public <T> void sendMessage(String topic, T message) {
        producer.beginTransaction();
        try {
            System.out.println("Sending message to kafka, the topic is: " + topic + ", the message is: " + message);
            producer.send(new ProducerRecord<>(topic, null, new ObjectMapper().writeValueAsString(message)));
            producer.commitTransaction();
            System.out.println("Message sent successfully.");
        } catch (Exception e) {
            producer.abortTransaction();
            e.printStackTrace();
        }
    }
}
