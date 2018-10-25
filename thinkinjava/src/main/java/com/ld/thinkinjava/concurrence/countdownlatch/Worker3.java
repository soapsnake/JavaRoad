package com.ld.thinkinjava.concurrence.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by liudun on 2017/7/21.
 */
public class Worker3 implements Runnable {

    private CountDownLatch countDownLatch;

    Worker3(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("worker3 thread");
        System.out.println("worker3 start working...");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("worker3 finish");
        countDownLatch.countDown();
        System.out.println("CountDownLatch count = " + countDownLatch.getCount());

    }
}
