package com.papupupu.producer.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;


@Component
public class ProducerRunner implements ApplicationRunner {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        for (int i = 0; i < 10; i++){
//            String msg = "消息 ：" + i;
//            kafkaTemplate.send("itcast-topic", msg);
//            System.out.println(msg);
//        }
//        Integer i = 0;
//        while (true){
//            kafkaTemplate.send("itcast-topic", "消息" + ++i);
//            Thread.sleep(1000);
//        }

        while (true){
            kafkaTemplate.send("log")
        }
    }
}
