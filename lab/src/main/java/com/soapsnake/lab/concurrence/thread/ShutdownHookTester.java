package com.soapsnake.lab.concurrence.thread;

import lombok.SneakyThrows;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-10-31
 */
public class ShutdownHookTester {

    public static void main(String[] args) throws InterruptedException {

        //系统级别钩子函数,main线程退出时会触发
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("shutdown Hook starting exiting...");
            }
        });
        Thread t1 = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                int retry = 5;
                while (retry > 0) {
                    Thread.sleep(1000L);
                    retry--;
                }
                System.out.println("t1 thread ready to shut down....");
            }
        };
        t1.start();
        t1.join();

        int retry = 5;
        while (retry > 0) {
            Thread.sleep(1000L);
            retry--;
        }
        System.out.println("main thread ready to shut down....");
    }
}
