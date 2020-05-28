package com.soapsnake.lab.concurrence.runnable.interrupt.reentrantlock;

public class Blocked2 implements Runnable {

    @Override
    public void run() {
        BlockedMutex blockedMutex = new BlockedMutex();
        // Auto-generated method stub
        System.out.println("等待BlockMutex类中的f方法");
        blockedMutex.f();
        System.out.println("BlockMutex类中的f方法执行完毕");
    }
}
