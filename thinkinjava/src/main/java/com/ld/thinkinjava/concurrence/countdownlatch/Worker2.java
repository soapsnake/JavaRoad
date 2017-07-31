package com.ld.thinkinjava.concurrence.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by liudun on 2017/7/21.
 */
public class Worker2 implements Runnable{

    private CountDownLatch countDownLatch;

    Worker2(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        Thread.currentThread().setName("worker2 thread");
        System.out.println("worker2 start working...");

        for (int i = 0;i<10;i++){
            try {
                Thread.sleep(2200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("worker2 finish");
        countDownLatch.countDown();
        System.out.println("CountDownLatch count = " +countDownLatch.getCount());


    }
}
