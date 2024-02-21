package com.paupupu.common.result;


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

}