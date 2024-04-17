package com.soapsnake.lab.thread;

public class ThreadOrder {

    public static void main(String[] args) {
        //线程的执行顺序
        final Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": is running");
            }
        });

        final Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.join();
                    System.out.println(Thread.currentThread().getName() + ": is running");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        final Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t2.join();
                    System.out.println(Thread.currentThread().getName() + ": is running");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t3.start();
        t2.start();
        t1.start();
    }
}
