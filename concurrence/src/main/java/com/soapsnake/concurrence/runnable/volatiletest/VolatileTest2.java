package com.soapsnake.concurrence.runnable.volatiletest;

import java.util.concurrent.CountDownLatch;

public class VolatileTest2 {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            CountDownLatch latch = new CountDownLatch(1);

            //如果这个线程先执行,那么y的值永远不可能为0
            Thread one = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                }

                //如果下面两句不进行指令重排序,令x = b = 0 抢先执行,那么x将永远不会为0
                a = 1;
                x = b;
            });

            //如果这个线程先执行,那么x的值永远不可能为0
            Thread other = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                }
                //如果下面两句不进行重排序,令y = a =0抢先执行,那么y的值永远不可能为0
                b = 1;
                y = a;
            });
            one.start();
            other.start();
            latch.countDown();
            one.join();
            other.join();

            String result = "第" + i + "次 (" + x + "," + y + "）";
            if (x == 0 && y == 0) {
                System.err.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }
}
