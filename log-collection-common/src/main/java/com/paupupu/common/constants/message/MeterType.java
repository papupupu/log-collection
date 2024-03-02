package com.paupupu.common.constants.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeterType {
    static Map<String, Integer> map = new HashMap<>();
    static {
        List<String> testPlafroms = TestPlaform.getTestPlafrom();
        for(int i = 0; i < testPlafroms.size(); i++){
            map.put(testPlafroms.get(i), i);
        }
    }

    public static int get(String key){
        return map.get(key);
    }
}
