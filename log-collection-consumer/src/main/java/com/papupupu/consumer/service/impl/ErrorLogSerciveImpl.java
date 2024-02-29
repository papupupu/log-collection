package com.papupupu.consumer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.papupupu.consumer.mapper.ErrorLogMapper;
import com.papupupu.consumer.model.pojo.ErrorLog;
import com.papupupu.consumer.service.ErrorLogService;
import org.springframework.stereotype.Service;


@Service
public class ErrorLogSerciveImpl extends ServiceImpl<ErrorLogMapper, ErrorLog> implements ErrorLogService {
}
