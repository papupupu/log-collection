package com.paupupu.common.constants.message;

import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MachineId {

    //设置默认机器数量是10
    static private Integer machineIdNum = 10;

    static  List<Integer> machineIdList = new ArrayList<>();
    static private Random random = new Random();

    static {
        for(int i = 0; i < machineIdNum; i++){
           machineIdList.add(i);
        }
    }

    public static Integer getRandom(){
        return machineIdList.get(random.nextInt(machineIdNum));
    }

}
