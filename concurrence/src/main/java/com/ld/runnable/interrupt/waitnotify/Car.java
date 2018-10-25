package com.ld.runnable.interrupt.waitnotify;

public class Car {
    private boolean waxOn = false;

    public synchronized void waxed() {   //打蜡
        waxOn = true;    //状态:已上蜡
        notifyAll();    //通知所有线程,自己已经上蜡,你们可以抛光了
    }

    public synchronized void buffed() {   //抛光
        waxOn = false;  //状态:已去腊
        notifyAll();
    }

    public synchronized void waitForWaxing() throws InterruptedException {  //等待上蜡
        while (waxOn == false) {
            wait();      //假如还没有上蜡,则等待上蜡
        }
    }

    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn == true) {
            wait();       //假如还没有抛光,则等待抛光
        }
    }
}
