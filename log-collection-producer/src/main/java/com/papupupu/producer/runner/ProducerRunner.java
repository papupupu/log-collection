package com.papupupu.producer.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.papupupu.model.log.pojo.Message;
import com.papupupu.producer.common.constant.FileConstants;
import com.papupupu.producer.config.FileFilterConfig;
import com.paupupu.common.constants.message.LogLevel;
import com.paupupu.common.constants.message.TestPlaform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    private FileFilterConfig fileFilterConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        Logger dailyLogger = LoggerFactory.getLogger("dailyLogger");
        fileFilterConfig.FileFilter(FileConstants.ROOTPATH, "daily.log");

        while (true){
            String log = objectMapper.writeValueAsString(Message.send("03", "031523400019", "15030315234000198000000000058FCCC4EDC80514", TestPlaform.CORRECTOR_INITIAL_CHECK, LogLevel.INFO));
//            kafkaTemplate.send("log-topic", msg);
            System.out.println(log);
            dailyLogger.error(log);
            Thread.sleep(3000);
        }
    }
}
