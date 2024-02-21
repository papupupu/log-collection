package com.papupupu.model.log.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import junit.framework.TestCase;

public class MessageTest extends TestCase {

    public void testAllocate() throws JsonProcessingException {
        Message allocate = Message.allocate("03", "031523400035", "150303152340003500000000008533031523400035B4A95D751A0A031523400035000144118705594415000360036000000000100000000000000001000000000020000010100001000004010810010000100003081003000011000608100600001600080810100000200000200000100000000050000050000000011000010000000031353033303100000000000000000000D214");
        System.out.println(allocate);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonAllocate = objectMapper.writeValueAsString(allocate);
        System.out.println(jsonAllocate);

    }

    public void testReceive() {

    }

    public void testSend() {
    }

    public void testUpload() {
    }
}