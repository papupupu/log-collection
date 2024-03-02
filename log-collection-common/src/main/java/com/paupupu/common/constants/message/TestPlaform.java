package com.paupupu.common.constants.message;

import lombok.Getter;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestPlaform {
    public static final String ULTRASOUND_INITIAL_CHECK = "超声波初检";
    public static final String MEMBRANE_METER_INITIAL_CHECK = "膜式表初检";
    public static final String CORRECTOR_INITIAL_CHECK = "修正仪初检";
    public static final String COMMAND = "命令";

    @Getter
    private static List<String> testPlafrom = new ArrayList<>();
    static {
        testPlafrom.add(ULTRASOUND_INITIAL_CHECK);
        testPlafrom.add(MEMBRANE_METER_INITIAL_CHECK);
        testPlafrom.add(CORRECTOR_INITIAL_CHECK);
        testPlafrom.add(COMMAND);
    }

    private static Random random = new Random();

    static public String getRandom(){
        return testPlafrom.get(random.nextInt(testPlafrom.size()));
    }
}
