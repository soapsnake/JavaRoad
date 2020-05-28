package com.soapsnake.lab.concurrence.multithread.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool {

    public static void main(String[] args) {

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);

        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("测试定时1秒任务");
            }
        }, 1, 1, TimeUnit.SECONDS);

    }
}
