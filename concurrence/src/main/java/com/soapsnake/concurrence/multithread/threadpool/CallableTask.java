package com.soapsnake.concurrence.multithread.threadpool;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<Long> {

    private long time;

    public CallableTask(long time) {
        this.time = time;
    }
    @Override
    public Long call() throws Exception {
        System.out.println("callable task...");
        Thread.sleep(time);
        return time;
    }
}
