package com.papupupu.consumer.listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.papupupu.model.log.pojo.Message;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
@Log4j2
public class LogListener {

    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());


    @KafkaListener(topics = "log-topic")
    public void test(String message) throws JsonProcessingException {
        System.out.println(message);
    }



}
