package com.papupupu.consumer.model.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.NoArgsConstructor;

@TableName(value = "log_error", autoResultMap = true)
@NoArgsConstructor
public class ErrorLog extends Log{


    public ErrorLog(Log log){
        this.
                setData(log.getData()).
                setDate(log.getDate()).
                setTime(log.getTime()).
                setLogLevel(log.getLogLevel()).
                setType(log.getType()).
//                setId(log.getId()).
                setMachineId(log.getMachineId()).
                setMeterType(log.getMeterType()).
                setTestPlatform(log.getTestPlatform()).
                setMeterId(log.getMeterId());
    }
}
