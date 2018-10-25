package com.ld.runnable.interrupt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Interrupting {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException {
        Future<?> future = executorService.submit(r);  //启动并且运行r线程
        TimeUnit.MILLISECONDS.sleep(100); //主线程睡100秒
        System.out.println("Interrupting" + r.getClass().getName());
        future.cancel(true);    //线程终止
        System.out.println("Interrupt send to " + r.getClass().getName());
    }

    public static void main(String[] args) throws Exception {
        test(new SleepBlocked());            //启动睡线程
        test(new IOBlocked(System.in));    //启动IO线程
        test(new SynchronizedBlocked());   //启动同步阻塞线程
        TimeUnit.SECONDS.sleep(3);  //主线程睡3秒
        System.out.println("Aborting with system.exit(0)");    //被中断
        System.exit(0);     //
    }
}
