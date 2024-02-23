package com.papupupu.model.log.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.paupupu.common.inet.Inet;
import junit.framework.TestCase;


import javax.swing.plaf.SpinnerUI;
import java.net.*;

public class MessageTest extends TestCase {

    private ObjectMapper objectMapper = new ObjectMapper();
    {
                objectMapper.registerModule(new JavaTimeModule());

    }
//        objectMapper.registerModule(new JavaTimeModule());

    public void testAllocate() throws JsonProcessingException {
        Message allocate = Message.allocate("03", "031523400035", "150303152340003500000000008533031523400035B4A95D751A0A031523400035000144118705594415000360036000000000100000000000000001000000000020000010100001000004010810010000100003081003000011000608100600001600080810100000200000200000100000000050000050000000011000010000000031353033303100000000000000000000D214");
        System.out.println(allocate);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
        String jsonAllocate = objectMapper.writeValueAsString(allocate);
        System.out.println(jsonAllocate);

    }

    public void testReceive() throws  JsonProcessingException, SocketException {
        Message receive = Message.receive("03", "031523400035", "15030315234000350000000000475103152340003500014411870559441500012301131103330003DC5AFD5EFFFF03600360000000001000000000000000010000000000200000101000010000FFFFFFFFFFFFFFFFC514");
        System.out.println(receive);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String string = objectMapper.writeValueAsString(receive);
        System.out.println(string);
    }

    public void testSend() throws UnknownHostException, SocketException, JsonProcessingException {
        Message send = Message.send("03", "031523400019", "15030315234000198000000000058FCCC4EDC80514");
        System.out.println(send);
        String string = objectMapper.writeValueAsString(send);
        System.out.println(string);
    }

    public void testUpload() throws JsonProcessingException {
        Message upload = Message.upload("03", "031523400019", "15030315234000198000000000058FCCC4EDC80514", "AAAAAAAAAA0A");
        System.out.println(upload);
        String string = objectMapper.writeValueAsString(upload);
        System.out.println(string);
    }

    public void test01(){
        Message upload = Message.upload("03", "031523400019", "15030315234000198000000000058FCCC4EDC80514", "AAAAAAAAAA0A");
        System.out.println(upload);
    }
}