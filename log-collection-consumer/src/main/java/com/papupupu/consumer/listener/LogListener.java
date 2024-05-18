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

import java.sql.DatabaseMetaData;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.SimpleFormatter;

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
//                    System.out.println(message);
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

//        LocalDate logDate = log.getDate();
//        LocalTime logTime = log.getTime();
//        LocalDateTime logDateTime = LocalDateTime.of(logDate, logTime);
//
//        // 获取当前系统时间
//        LocalDateTime nowDateTime = LocalDateTime.now();
//
//        // 转换为 java.util.Date 对象
//        Date logDateUtil = Date.from(logDateTime.atZone(ZoneId.systemDefault()).toInstant());
//        Date nowDateUtil = Date.from(nowDateTime.atZone(ZoneId.systemDefault()).toInstant());
//
//        // 计算差值
//        long elapsedTimeInMillis = nowDateUtil.getTime() - logDateUtil.getTime();
//
//        System.out.println(elapsedTimeInMillis);

    }
}
