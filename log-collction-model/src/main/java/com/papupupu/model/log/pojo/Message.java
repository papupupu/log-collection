package com.papupupu.model.log.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.paupupu.common.constants.message.TypeConstants;
import jdk.dynalink.linker.GuardingTypeConverterFactory;
import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.reflect.Type;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@Data
@Accessors(chain = true)
public class Message {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @JsonFormat(pattern = "HH:mm:ss SSS")
    private LocalTime time;

    private  String type;
    private MessageData data;



    private  Message(){
        this.setTime(LocalTime.now()).setDate(LocalDate.now());
    }

    public static Message allocate(String meter_type, String meter_id, String data){
        return new Message().setType(TypeConstants.ALLOCATE).setData(MessageData.allocate(meter_type, meter_id, data));
    }

    public static Message receive(String meter_type, String meter_id, String data) throws  SocketException {
        return new Message().setType(TypeConstants.RECEIVE).setData(MessageData.receive(meter_type, meter_id, data));
    }

    public static Message send(String meter_type, String meter_id, String data) throws  SocketException {
        return new Message().setType(TypeConstants.SEND).setData(MessageData.send(meter_type, meter_id, data));
    }

    public static  Message upload(String meter_type, String meter_id, String data, String result){
        return new Message().setType(TypeConstants.UPLOAD).setData(MessageData.upload(meter_type, meter_id, data, result));
    }



}
