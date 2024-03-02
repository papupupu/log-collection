package com.papupupu.model.log.pojo;


//import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paupupu.common.constants.message.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.aop.aspectj.annotation.MetadataAwareAspectInstanceFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

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

    public static Message allocate(String meter_type, String meter_id, String data, String testPlaform, String logLevel, Integer machineId){
        return new Message().setType(Type.ALLOCATE).setData(MessageData.allocate(meter_type, meter_id, data)).setTestPlaform(testPlaform).setLogLevel(logLevel).setMachineId(machineId);
    }

    public static Message receive(String meter_type, String meter_id, String data, String testPlaform, String logLevel, Integer machineId) throws  SocketException {
        return new Message().setType(Type.RECEIVE).setData(MessageData.receive(meter_type, meter_id, data)).setTestPlaform(testPlaform).setLogLevel(logLevel).setMachineId(machineId);
    }

    public static Message send(String meter_type, String meter_id, String data, String testPlaform, String logLevel, Integer machineId) throws  SocketException {
        return new Message().setType(Type.SEND).setData(MessageData.send(meter_type, meter_id, data)).setTestPlaform(testPlaform).setLogLevel(logLevel).setMachineId(machineId);
    }

    public static  Message upload(String meter_type, String meter_id, String data, String result, String testPlaform, String logLevel, Integer machineId){
        return new Message().setType(Type.UPLOAD).setData(MessageData.upload(meter_type, meter_id, data, result)).setTestPlaform(testPlaform).setLogLevel(logLevel).setMachineId(machineId);
    }

    public static Message getRandom() throws SocketException {
        Message message = null;

        String testPlatform = TestPlaform.getRandom();
        String meterType = String.format("%02d", MeterType.get(testPlatform));
        String meterId = meterType + "1523400035";
        String data ="15" + meterType +meterId + "00000000008533031523400035B4A95D751A0A031523480035800144118705594415000360036000BOOOGBICOOOOOOOCOOOCOOO1COOOOOOO0020000C1C10000100000401081001088010888388108388881188868810868888168888881B1BBBBB288BBB200000100008000050000850000000011000010088000031353033303108000000000080000000D214";
        String result = "AAAAAAAAAA0A";
        String logLevel = LogLevel.getRandom();
        Integer machineId = MachineId.getRandom()
;
        switch (Type.getRandom()) {
            case Type.SEND:
                message = Message.send(meterType, meterId, data, testPlatform, logLevel, machineId);
                break;
            case Type.UPLOAD:
                message =  Message.upload(meterType, meterId, data, result, testPlatform, logLevel, machineId);
                break;
            case Type.RECEIVE:
                message = Message.receive(meterType, meterId, data, testPlatform, logLevel, machineId);
            case Type.ALLOCATE:
                message = Message.allocate(meterType, meterId, data, testPlatform, logLevel, machineId);
        }
        return  message;
    }



}
