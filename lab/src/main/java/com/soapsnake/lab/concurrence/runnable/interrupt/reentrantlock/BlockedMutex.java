package com.soapsnake.lab.concurrence.runnable.interrupt.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockedMutex {

    private Lock lock = new ReentrantLock();

    public BlockedMutex() {
        lock.lock();
    }

    public void f() {
        try {
            lock.lockInterruptibly();
            System.out.println("在f方法当中获取锁");
        } catch (InterruptedException e) {
            //: handle exception
            System.out.println("interrupt异常从被锁住的方法当中产生了!!!!!!!");
        }
    }
}
