package com.papupupu.producer.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.papupupu.model.log.pojo.Message;
import com.papupupu.producer.common.constant.FileConstants;
import com.papupupu.producer.config.FileFilterConfig;
import com.paupupu.common.constants.message.LogLevel;
import com.paupupu.common.constants.message.MachineId;
import com.paupupu.common.constants.message.TestPlaform;
import com.paupupu.common.constants.monitor.MonitorWay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.TimeUnit;

import static com.paupupu.common.constants.monitor.MonitorWay.FULL_MONITORING;
import static com.paupupu.common.constants.monitor.MonitorWay.INCREMENTAL_MONITORING;


@Component
public class ProducerRunner implements ApplicationRunner {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private FileFilterConfig fileFilterConfig;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Value("${server.port}")
    private String port;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        Logger dailyLogger = LoggerFactory.getLogger("dailyLogger");

        Runnable runnable = () -> {

            while (true) {
                stringRedisTemplate.opsForValue().set(port, "exist", 10, TimeUnit.SECONDS);
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread monitorThread = new Thread(runnable);
        monitorThread.start();

        //增加增量监控
        fileFilterConfig.FileFilter(FileConstants.ROOTPATH, "daily.log", INCREMENTAL_MONITORING);

        //对文件增加全量监控
        fileFilterConfig.FileFilter(FileConstants.ROOTPATH, "full.log", FULL_MONITORING);

        while (true) {
            Message message = Message.getRandom();
            String log = objectMapper.writeValueAsString(message);
//            String log = objectMapper.writeValueAsString(Message.send("03", "031523400019", "15030315234000198000000000058FCCC4EDC80514", TestPlaform.CORRECTOR_INITIAL_CHECK, LogLevel.INFO, MachineId.getRandom()));
//            kafkaTemplate.send("log-topic", msg);
//            System.out.println(log);
            dailyLogger.error(log);
            Thread.sleep(3000);
        }
    }
}
