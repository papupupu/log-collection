package com.paupupu.common.result;

import com.paupupu.common.constants.Result.CodeConstants;
import com.paupupu.common.constants.Result.MsgConstants;
import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Objects;





@Accessors(chain = true)
@Data
public class Result<T> {
    private Integer code;
    private String msg;

    HashMap<String, Object> map;
    private T data;


    public static  Result success(){
        return new Result().setCode(CodeConstants.SUCCESS).setMsg(MsgConstants.SUCCESS);
    }

    public static <T> Result success(T data){
        return Result.success().setData(data);
    }

}
