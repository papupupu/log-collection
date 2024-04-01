package com.papupupu.producer.config;


import com.jayway.jsonpath.internal.filter.ValueNode;
import com.papupupu.producer.listener.FileListenerFull;
import com.papupupu.producer.listener.FileListenerIncremental;
import com.paupupu.common.constants.monitor.MonitorWay;
import org.apache.commons.io.filefilter.AbstractFileFilter;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.TimeUnit;

import static com.paupupu.common.constants.monitor.MonitorWay.FULL_MONITORING;
import static com.paupupu.common.constants.monitor.MonitorWay.INCREMENTAL_MONITORING;

@Configuration
public class FileFilterConfig {
    @Autowired
    private FileListenerIncremental fileListenerIncremental;

    @Autowired
    private FileListenerFull fileListenerFull;


    public void FileFilter(String rootPath, String fileName, String monitorWay){
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

        FileAlterationListenerAdaptor fileAlterationListener;
        switch (monitorWay){
            case INCREMENTAL_MONITORING:
                fileAlterationListener = fileListenerIncremental;
                break;
            case FULL_MONITORING:
                fileAlterationListener = fileListenerFull;
                break;
            default:
                fileAlterationListener = fileListenerIncremental;
        }
        observer.addListener(fileAlterationListener);
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        try {
            monitor.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
