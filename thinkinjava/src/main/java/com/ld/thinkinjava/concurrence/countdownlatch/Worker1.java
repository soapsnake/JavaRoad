package com.ld.thinkinjava.concurrence.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by liudun on 2017/7/21.
 */
public class Worker1 implements Runnable {
    private CountDownLatch countDownLatch;

    public Worker1(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        Thread.currentThread().setName("worker1 thread");
        System.out.println("worker1 start working...");

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("worker1 finish his job...");
        countDownLatch.countDown();
        System.out.println("CountDownLatch count = " + countDownLatch.getCount());
    }
}
