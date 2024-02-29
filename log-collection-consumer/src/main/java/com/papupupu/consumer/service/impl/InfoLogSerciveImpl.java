package com.papupupu.consumer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.papupupu.consumer.mapper.ErrorLogMapper;
import com.papupupu.consumer.mapper.InfoLogMapper;
import com.papupupu.consumer.model.pojo.ErrorLog;
import com.papupupu.consumer.model.pojo.InfoLog;
import com.papupupu.consumer.service.ErrorLogService;
import com.papupupu.consumer.service.InfoLogService;
import org.springframework.stereotype.Service;


@Service
public class InfoLogSerciveImpl extends ServiceImpl<InfoLogMapper, InfoLog> implements InfoLogService {
}
