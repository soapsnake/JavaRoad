package com.soapsnake.thinkinjava.concurrence.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by liudun on 2017/7/21.
 */
public class Monitor {

    public static void main(String[] args) {
        /**
         * CountDownLatch相当于一个可被多线程共享的一个计数器,
         * 可把这个计数器设置进多个工作线程,在各个线程中对计数器进行操作
         * 创建countDownLatch时可以指定一个初始值,各个线程会使用countdown()对该值进行扣减
         * 要被阻塞的线程中调用await()方法后就会被阻塞,直到count值减到0,该线程才会解除阻塞
         */
        CountDownLatch countDownLatch = new CountDownLatch(3);


        //Executor是线程池的父接口
        Executor executor = Executors.newFixedThreadPool(3);
        executor.execute(new Worker1(countDownLatch));

        executor.execute(new Worker2(countDownLatch));

        executor.execute(new Worker3(countDownLatch));


        try {
            System.out.println(countDownLatch.getCount());
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("CountDownLatch count = " + countDownLatch.getCount());
        System.out.println("job is finish...");


    }


}
