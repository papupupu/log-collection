package com.paupupu.common.result;


import com.paupupu.common.constants.message.LogLevel;
import com.paupupu.common.constants.message.MachineId;
import com.paupupu.common.constants.message.TestPlaform;
import com.paupupu.common.constants.message.Type;
import org.junit.Test;

public class ResultTest {
    @Test
    public void tes01(){
        Result success = Result.success();
        System.out.println(success);
        String msg ="测试一下";
        Result success1 = Result.success(msg);
        System.out.println(success1);

    }
    @Test
    public void test02(){
        System.out.println(MachineId.getRandom());
        System.out.println(TestPlaform.getRandom());
        System.out.println(LogLevel.getRandom());
        System.out.println(Type.getRandom());
    }

}