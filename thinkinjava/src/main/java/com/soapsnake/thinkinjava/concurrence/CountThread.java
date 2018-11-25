package com.soapsnake.thinkinjava.concurrence;

/**
 * Created by liudun on 2017/9/14.
 */
public class CountThread implements Runnable {
    @Override
    public void run() {

        System.out.println("计算线程开始工作...");

        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("计算线程结束工作...");

    }
}
