package com.soapsnake.concurrence.runnable.interrupt.waitnotify;

import java.util.concurrent.TimeUnit;

public class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car c) {
        this.car = c;
    }


    @Override
    public void run() {
        // Auto-generated method stub
        try {
            while (!Thread.interrupted()) {
                System.out.println("上蜡!!!!!!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            //: handle exception
            System.out.println("线程遭遇interrupted");
        }
        System.out.println("打蜡线程执行完毕");
    }

}
