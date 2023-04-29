package com.cc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactory implements ThreadFactory {

    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public CustomThreadFactory(String groupName) {
        SecurityManager s = System.getSecurityManager();
        ThreadGroup group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        namePrefix = groupName + "-" +
                new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()) +
                "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(Thread.currentThread().getThreadGroup(), r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
