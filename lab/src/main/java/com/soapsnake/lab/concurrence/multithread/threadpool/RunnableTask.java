package com.soapsnake.lab.concurrence.multithread.threadpool;

public class RunnableTask implements Runnable {
    @Override
    public void run() {
        System.out.println("runabble task");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
