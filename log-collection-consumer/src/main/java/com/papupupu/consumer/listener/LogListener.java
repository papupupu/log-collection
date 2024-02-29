package com.papupupu.consumer.listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.papupupu.consumer.model.pojo.ErrorLog;
import com.papupupu.consumer.model.pojo.InfoLog;
import com.papupupu.consumer.model.pojo.Log;
import com.papupupu.consumer.model.pojo.WarningLog;
import com.papupupu.consumer.service.ErrorLogService;
import com.papupupu.consumer.service.InfoLogService;
import com.papupupu.consumer.service.WarningLogService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;

import com.paupupu.common.constants.message.LogLevel;


@Component
@Log4j2
public class LogListener {

    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    @Autowired
    private ErrorLogService errorLogService;
    @Autowired
    private InfoLogService infoLogService;
    @Autowired
    private WarningLogService warningLogService;

    @KafkaListener(topics = "log-topic")
    public void test(String message) throws JsonProcessingException, ParseException {
//        System.out.println(message);
        Log log = Log.MessageToLog(message);
        System.out.println(log.getLogLevel());
        switch (log.getLogLevel()) {
            case LogLevel.ERROR:
                try {
                    System.out.println(message);
                    errorLogService.save((ErrorLog) log);
                    break;
                } catch (Exception ignored) {
                }

            case LogLevel.WARNING:
                try {
                    System.out.println(message);
                    warningLogService.save((WarningLog) log);
                    break;
                } catch (Exception ignored) {
                }

            case LogLevel.INFO:
                try {
                    System.out.println(message);
                    infoLogService.save((InfoLog) log);
                    break;
                } catch (Exception ignored) {
                }
        }


//        System.out.println(log);
//        System.out.println(log1);

    }
}
