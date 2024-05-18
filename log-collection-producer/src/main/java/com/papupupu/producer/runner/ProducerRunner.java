package com.papupupu.producer.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.papupupu.model.log.pojo.Message;
import com.papupupu.producer.common.constant.FileConstants;
import com.papupupu.producer.config.CollectionConfig;
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

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.paupupu.common.constants.monitor.MonitorWay.FULL_MONITORING;
import static com.paupupu.common.constants.monitor.MonitorWay.INCREMENTAL_MONITORING;


@Component
public class ProducerRunner implements ApplicationRunner {


    @Autowired
    private FileFilterConfig fileFilterConfig;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private CollectionConfig collectionConfig;


    @Value("${server.port}")
    private String port;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        Logger dailyLogger1 = LoggerFactory.getLogger("dailyLogger1");
        Logger dailyLogger2 = LoggerFactory.getLogger("dailyLogger2");


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

        List<String> paths = collectionConfig.getPaths();
        List<String> types = collectionConfig.getTypes();



        if (paths.size() != types.size()) {
            throw new RuntimeException("采集程序配置有误");
        }
        for (int i = 0; i < paths.size(); i++) {
            String path = paths.get(i);
            String type = types.get(i);
            int lastIndex = path.lastIndexOf('/');
            String rootPath = path.substring(0, lastIndex + 1);
            String fileName = path.substring(lastIndex + 1);
            fileFilterConfig.FileFilter(rootPath, fileName, type);
        }

//        TimeUnit.MINUTES.sleep(1000);


        while (true) {
            Message message = Message.getRandom();
            String log1 = objectMapper.writeValueAsString(message);
            String log2 = objectMapper.writeValueAsString(message);
            dailyLogger1.error(log1);
            dailyLogger2.error(log2);
            Thread.sleep(1000);
        }
    }
}
