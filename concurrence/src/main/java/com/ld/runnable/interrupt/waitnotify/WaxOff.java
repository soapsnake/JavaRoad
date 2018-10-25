package com.ld.runnable.interrupt.waitnotify;

import java.util.concurrent.TimeUnit;

public class WaxOff implements Runnable {

    private Car car;

    public WaxOff(Car car) {
        // Auto-generated method stub
        this.car = car;
    }


    @Override
    public void run() {
        // Auto-generated method stub
        try {
            while (!Thread.interrupted()) {
                car.waitForBuffing();
                System.out.println("抛光!!!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
                car.waitForWaxing();
            }

        } catch (InterruptedException e) {
            //: handle exception
            System.out.println("抛光线程遭遇interrupted");
        }
        System.out.println("抛光线程执行完毕");
    }
}
