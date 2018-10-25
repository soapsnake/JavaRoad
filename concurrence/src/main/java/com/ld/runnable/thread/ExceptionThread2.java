package com.ld.runnable.thread;

public class ExceptionThread2 implements Runnable {

    @Override
    public void run() {

        Thread t = Thread.currentThread();

        System.out.println("run() by:" + t + " eh= " + t.getUncaughtExceptionHandler());

        throw new RuntimeException();
    }
}
