package com.papupupu.producer.listener;


import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

@Component
public class FileListenerFull extends FileAlterationListenerAdaptor {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    private static long lastReadPosition = 0;


    @Override
    public void onStart(FileAlterationObserver observer) {
        File directory = observer.getDirectory();
        FileFilter fileFilter = observer.getFileFilter();
        File[] files = directory.listFiles(fileFilter);


        for (File file : files) {
            try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
                randomAccessFile.seek(lastReadPosition);
                String msg;
                while ((msg = randomAccessFile.readLine()) != null) {
                    String decodedMsg = new String(msg.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                    kafkaTemplate.send("log-topic", decodedMsg);
                    System.out.println(decodedMsg);
                }
//            synchronized(FileListener.class){
                lastReadPosition = randomAccessFile.getFilePointer();
//            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
