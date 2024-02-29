package com.papupupu.model.log.pojo;


//import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paupupu.common.constants.message.Type;
import lombok.Data;
import lombok.experimental.Accessors;

import java.net.SocketException;
import java.time.LocalDate;
import java.time.LocalTime;


@Data
@Accessors(chain = true)
public class Message {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @JsonFormat(pattern = "HH:mm:ss SSS")
    private LocalTime time;

    private  String type;
    private MessageData data;
    private  Integer machineId;
    private String testPlaform;
    private String logLevel;



    private  Message(){
        this.setTime(LocalTime.now()).setDate(LocalDate.now());
    }

    public static Message allocate(String meter_type, String meter_id, String data, String testPlaform, String logLevel){
        return new Message().setType(Type.ALLOCATE).setData(MessageData.allocate(meter_type, meter_id, data)).setTestPlaform(testPlaform).setLogLevel(logLevel);
    }

    public static Message receive(String meter_type, String meter_id, String data, String testPlaform, String logLevel) throws  SocketException {
        return new Message().setType(Type.RECEIVE).setData(MessageData.receive(meter_type, meter_id, data)).setTestPlaform(testPlaform).setLogLevel(logLevel);
    }

    public static Message send(String meter_type, String meter_id, String data, String testPlaform, String logLevel) throws  SocketException {
        return new Message().setType(Type.SEND).setData(MessageData.send(meter_type, meter_id, data)).setTestPlaform(testPlaform).setLogLevel(logLevel);
    }

    public static  Message upload(String meter_type, String meter_id, String data, String result, String testPlaform, String logLevel){
        return new Message().setType(Type.UPLOAD).setData(MessageData.upload(meter_type, meter_id, data, result)).setTestPlaform(testPlaform).setLogLevel(logLevel);
    }



}
