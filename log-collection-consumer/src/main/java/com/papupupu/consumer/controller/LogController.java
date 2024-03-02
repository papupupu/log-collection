package com.papupupu.consumer.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.papupupu.consumer.model.pojo.InfoLog;
import com.papupupu.consumer.model.pojo.Log;
import com.papupupu.consumer.service.ErrorLogService;
import com.papupupu.consumer.service.InfoLogService;
import com.papupupu.consumer.service.WarningLogService;
import com.paupupu.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.adapter.AbstractReactiveWebInitializer;

import java.text.ParseException;


@RestController
@RequestMapping("log")
public class LogController {

    @Autowired
    private ErrorLogService errorLogService;

    @Autowired
    private WarningLogService warningLogService;

    @Autowired
    private InfoLogService infoLogService;


    @RequestMapping("/test")
    public Result test(){
        return Result.success(errorLogService.getById(1));
    }

    @RequestMapping("/test1")
    public Result test1() throws ParseException, JsonProcessingException {
        Log log = Log.MessageToLog("{\"date\":\"2024-03-02\",\"time\":\"23:56:55 906\",\"type\":\"send\",\"data\":{\"ip\":\"192.168.31.21\",\"meter_type\":\"03\",\"meter_id\":\"031523400019\",\"data\":\"15030315234000198000000000058FCCC4EDC80514\"},\"machineId\":9,\"testPlaform\":\"修正仪初检\",\"logLevel\":\"info\"}\n");
        InfoLog infoLog = new InfoLog(log);
        System.out.println(infoLog.getData());
        return Result.success(infoLogService.save(infoLog));
    }

}
