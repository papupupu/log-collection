package com.papupupu.consumer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.papupupu.consumer.mapper.ErrorLogMapper;
import com.papupupu.consumer.mapper.WarningLogMapper;
import com.papupupu.consumer.model.pojo.ErrorLog;
import com.papupupu.consumer.model.pojo.WarningLog;
import com.papupupu.consumer.service.ErrorLogService;
import com.papupupu.consumer.service.WarningLogService;
import org.springframework.stereotype.Service;


@Service
public class WarningLogSerciveImpl extends ServiceImpl<WarningLogMapper, WarningLog> implements WarningLogService {
}
