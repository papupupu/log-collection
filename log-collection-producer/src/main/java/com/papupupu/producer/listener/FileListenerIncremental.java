package com.papupupu.producer.listener;


import lombok.Synchronized;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

@Component
public class FileListenerIncremental extends FileAlterationListenerAdaptor {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    private static long lastReadPosition = 0;



    @Override
    public void onFileChange(File file) {
//        if(!file.getName().equals("daily.log")) return;

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            randomAccessFile.seek(lastReadPosition);
            String msg;
            while ((msg = randomAccessFile.readLine()) != null) {
                String decodedMsg = new String(msg.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                kafkaTemplate.send("log-topic", decodedMsg);
//                System.out.println(decodedMsg);
            }
//            synchronized(FileListener.class){
                lastReadPosition = randomAccessFile.getFilePointer();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
