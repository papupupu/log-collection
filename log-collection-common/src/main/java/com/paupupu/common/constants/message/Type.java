package com.paupupu.common.constants.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Type {
    public static final String ALLOCATE = "allocate";
    public static final String RECEIVE = "receive";
    public static final String SEND = "send";
    public static final String UPLOAD = "upload";

    private static List<String> typeList = new ArrayList<>();
    static {
        typeList.add(ALLOCATE);
        typeList.add(RECEIVE);
        typeList.add(SEND);
        typeList.add(UPLOAD);
    }

    private static Random random = new Random();

    static public String getRandom(){
        return typeList.get(random.nextInt(typeList.size()));
    }
}