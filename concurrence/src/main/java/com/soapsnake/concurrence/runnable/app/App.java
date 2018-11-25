package com.soapsnake.concurrence.runnable.app;

import com.soapsnake.concurrence.runnable.thread.RunnableThread;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        int i = 0;
        Runnable rthread = new RunnableThread();     //多态
        // rthread.run();         //经验证这种写法还是单线程的!!!!!!!!!!!!!!!!!!
        Thread t1 = new Thread(rthread);
        t1.start();     //t对象会自动调用runnable的run方法

        Thread t2 = new Thread(rthread);
        t2.start();

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello World!  " + Thread.currentThread().getName() + " " + i--);
        }
    }
}
