package com.soapsnake.concurrence.runnable.factory;

import java.util.concurrent.ThreadFactory;

import com.soapsnake.concurrence.runnable.handler.MyUncaughtExceptionHandler;

public class HandlerThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {

        Thread t = new Thread(r);
        System.out.println("create new thread!!" + t);

        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());  //把新创建的handler set给新创建的线程

        System.out.println("exception is: " + t.getUncaughtExceptionHandler());

        return t;

    }

}
