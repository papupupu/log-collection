package com.papupupu.consumer.model.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.Info;
import io.swagger.models.auth.In;
import lombok.NoArgsConstructor;

@TableName(value = "log_info", autoResultMap = true)
@NoArgsConstructor
public class InfoLog extends Log{
    public InfoLog(Log log){
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
