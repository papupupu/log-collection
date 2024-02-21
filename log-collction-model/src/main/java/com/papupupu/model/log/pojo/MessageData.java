package com.papupupu.model.log.pojo;


import lombok.Data;
import lombok.experimental.Accessors;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Data
@Accessors(chain = true)
public class MessageData {
    private String ip;
    private String meter_type;
    private String meter_id;
    private String data;
    private String result;

    static public MessageData allocate(String meter_type, String meter_id, String data){
        return new MessageData().setMeter_type(meter_type).setMeter_id(meter_id).setData(data);
    }

    static public MessageData receive(String meter_type, String meter_id) throws UnknownHostException {
        return new MessageData().setMeter_type(meter_type).setMeter_id(meter_id).setIp(InetAddress.getLocalHost().getHostAddress());
    }

    static public MessageData send(String meter_type, String meter_id, String data) throws UnknownHostException {
        return new MessageData().setMeter_type(meter_type).setMeter_id(meter_id).setData(data).setIp(InetAddress.getLocalHost().getHostAddress());
    }

    static public MessageData upload(String meter_type, String meter_id, String data, String result){
        return new MessageData().setMeter_type(meter_type).setMeter_id(meter_id).setData(data).setResult(result);
    }

}
