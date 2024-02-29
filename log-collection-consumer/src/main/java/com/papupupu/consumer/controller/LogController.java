package com.papupupu.consumer.controller;


import com.papupupu.consumer.service.ErrorLogService;
import com.papupupu.consumer.service.InfoLogService;
import com.papupupu.consumer.service.WarningLogService;
import com.paupupu.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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

}
