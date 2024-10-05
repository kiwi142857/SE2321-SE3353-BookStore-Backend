package com.web.bookstore.dto;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonSerialize
@JsonDeserialize
@NoArgsConstructor
// @AllArgsConstructor
public class KafkaPostOrderDTO {

    String receiver;
    String address;
    String tel;
    String userId;

    @JsonProperty("itemIds")
    List<Integer> items;

    // Constructor from PostOrderDTO and userId
    public KafkaPostOrderDTO(PostOrderDTO postOrderDTO, String userId) {
        this.receiver = postOrderDTO.getReceiver();
        this.address = postOrderDTO.getAddress();
        this.tel = postOrderDTO.getTel();
        this.userId = userId;
        this.items = postOrderDTO.getItems();
    }

}
