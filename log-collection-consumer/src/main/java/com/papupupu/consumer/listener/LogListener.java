package com.papupupu.consumer.listener;


import com.fasterxml.jackson.annotation.JsonInclude;
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
        Log log = Log.MessageToLog(message);

        try {
            switch (log.getLogLevel()) {
                case LogLevel.ERROR:
                    System.out.println(message);
                    errorLogService.save(new ErrorLog(log));
                    break;

                case LogLevel.WARNING:
                    warningLogService.save(new WarningLog(log));
                    break;

                case LogLevel.INFO:
                    InfoLog infoLog = new InfoLog(log);
                    infoLogService.save(infoLog);
                    break;
            }
        } catch (Exception e) {
            System.out.println("出现异常");
        }
    }
}
