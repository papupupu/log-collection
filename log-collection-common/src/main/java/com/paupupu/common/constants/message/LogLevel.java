package com.paupupu.common.constants.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogLevel {
    public static final String INFO = "info";
    public static final String WARNING = "warning";
    public static final String ERROR = "error";

    private static List<String> loglevelList = new ArrayList<>();
    static {
        loglevelList.add(INFO);
        loglevelList.add(WARNING);
        loglevelList.add(ERROR);
    }

    private static Random random = new Random();

    static public String getRandom(){
        return loglevelList.get(random.nextInt(loglevelList.size()));
    }
}
