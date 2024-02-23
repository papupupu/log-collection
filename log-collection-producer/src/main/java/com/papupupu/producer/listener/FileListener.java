package com.papupupu.producer.listener;


import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.RandomAccessFile;

@Component
public class FileListener extends FileAlterationListenerAdaptor {
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
                kafkaTemplate.send("log-topic", msg);
                System.out.println(msg);
            }
            lastReadPosition = randomAccessFile.getFilePointer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
