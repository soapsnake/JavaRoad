package com.soapsnake.concurrence.runnable.thread;

public class ExceptionThread implements Runnable {

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
