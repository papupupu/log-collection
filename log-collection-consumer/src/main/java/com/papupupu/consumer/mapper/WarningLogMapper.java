package com.papupupu.consumer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.papupupu.consumer.model.pojo.ErrorLog;
import com.papupupu.consumer.model.pojo.WarningLog;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface WarningLogMapper extends BaseMapper<WarningLog> {
}
