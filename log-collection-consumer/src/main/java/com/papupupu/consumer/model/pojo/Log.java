package com.papupupu.consumer.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.papupupu.model.log.pojo.MessageData;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Log {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "machine_id")
    private Integer machineId;

    @TableField(value = "test_platform")
    private String testPlatform;

    @TableField(value = "date")
    private Date date;

    @TableField(value = "time")
    private Date time;

    @TableField(value = "type")
    private String type;

    @TableField(value = "meter_type")
    private Integer meterType;

    @TableField(value = "meter_id")
    private Long meterId;

    @TableField(value = "data")
    private String data;
}
