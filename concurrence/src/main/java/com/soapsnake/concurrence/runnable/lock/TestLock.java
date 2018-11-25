package com.soapsnake.concurrence.runnable.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liudun on 2018/1/31.
 */
public class TestLock {

    public static void main(String[] args) {
        TestLock testLock = new TestLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                testLock.lockMethod();
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                testLock.lockMethod();
            }
        });
        t2.start();
    }

    void lockMethod() {

        Lock lock = new ReentrantLock();
        lock.lock();

        try {
            while (true) {
                Thread.sleep(2000);
                String threadName = Thread.currentThread().getName();
                System.out.println("thread: " + threadName + " is running...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }


}
