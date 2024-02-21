package com.papupupu.consumer.listener;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class LogListener {
    @KafkaListener(topics = "itcast-topic")
    public void test(String message){
        System.out.println(message);
    }



}
