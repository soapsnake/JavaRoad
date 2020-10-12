package com.soapsnake.lab.concurrence.runnable.thread;

public class ExceptionThread implements Runnable {

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
