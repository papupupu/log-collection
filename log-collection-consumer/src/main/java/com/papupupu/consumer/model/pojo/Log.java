package com.papupupu.consumer.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.papupupu.consumer.service.ErrorLogService;
import com.papupupu.model.log.pojo.Message;
import com.papupupu.model.log.pojo.MessageData;
import com.paupupu.common.constants.message.LogLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.stream.Location;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Log {
    private static ObjectMapper objectMapper  =  new ObjectMapper().registerModule(new JavaTimeModule());

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "machine_id")
    private Integer machineId;

    @TableField(value = "test_platform")
    private String testPlatform;

    @TableField(value = "date")
    private LocalDate date;

    @TableField(value = "time")
    private LocalTime  time;

    @TableField(value = "type")
    private String type;

    @TableField(value = "meter_type")
    private Integer meterType;

    @TableField(value = "meter_id")
    private String meterId;

    @TableField(value = "data")
    private String data;

    private String logLevel;
    public static Log MessageToLog(String msg) throws ParseException, JsonProcessingException {

        Message message = objectMapper.readValue(msg, Message.class);

        return new Log().
            setMachineId(message.getMachineId()).
            setTestPlatform(message.getTestPlaform()).
            setDate(message.getDate()).
            setTime(message.getTime()).
            setType(message.getType()).
            setMeterType(Integer.parseInt(message.getData().getMeter_type())).
            setMeterId(message.getData().getMeter_id()).
            setData(message.getData().getData()).
            setLogLevel(message.getLogLevel());
    }
}
