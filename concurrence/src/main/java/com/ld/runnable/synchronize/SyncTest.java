package com.ld.runnable.synchronize;

public class SyncTest {


    public static void main(String[] args) {
        SyncTest syncTest = new SyncTest();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    syncTest.methodA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread 1 ");
        t1.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                syncTest.methodB();
            }
        }, "thread 2 ").start();


        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //唤醒t1线程
        t1.interrupt();

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void methodA() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "is working on methodA");
        }
    }

    public synchronized void methodB() {
        System.out.println(Thread.currentThread().getName() + "is working on methodB");

    }

}
