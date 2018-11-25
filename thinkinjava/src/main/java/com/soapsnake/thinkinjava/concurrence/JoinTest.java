package com.soapsnake.thinkinjava.concurrence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liudun on 2017/9/14.
 */
public class JoinTest {

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(3);


        Thread worker = new Thread(new CountThread());

        worker.run();

        try {
            worker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main线程退出....");


    }
}
