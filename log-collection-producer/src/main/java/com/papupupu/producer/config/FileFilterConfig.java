package com.papupupu.producer.config;


import com.jayway.jsonpath.internal.filter.ValueNode;
import com.papupupu.producer.listener.FileListener;
import org.apache.commons.io.filefilter.AbstractFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.TimeUnit;

@Configuration
public class FileFilterConfig {
    @Autowired
    private FileListener fileListener;

    public void FileFilter(String rootPath, String fileName){
        //设置200毫秒轮询一次
        long interval =  200;
        FileAlterationObserver observer = null;
        if(StringUtils.isNotBlank(fileName)){
            AbstractFileFilter filter = new AbstractFileFilter(){
                @Override
                public boolean accept(File file) {
                    return fileName.equals(file.getName());
                }
            };
             observer = new FileAlterationObserver(rootPath, filter);
        }
        else {
             observer = new FileAlterationObserver(rootPath);
        }
        observer.addListener(fileListener);
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        try {
            monitor.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
