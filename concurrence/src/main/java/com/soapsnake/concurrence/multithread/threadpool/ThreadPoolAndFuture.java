package com.soapsnake.concurrence.multithread.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolAndFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Long start = System.currentTimeMillis();

        RunnableTask task = new RunnableTask();
        task.run();
        task.run();
        task.run();
        task.run();
        task.run();
        System.out.println("test 1 ,time is = " + (System.currentTimeMillis() - start) / 1000);

        Long start2 = System.currentTimeMillis();

        Future future1 = executorService.submit(new CallableTask(1000));

        Future future2 = executorService.submit(new CallableTask(2000));

        Future future3 = executorService.submit(new CallableTask(3000));

        Future future4 = executorService.submit(new CallableTask(2000));

        Future future5 = executorService.submit(new CallableTask(2323));

        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());
        System.out.println(future4.get());
        System.out.println(future5.get());

        System.out.println("test 2 ,time is = " + (System.currentTimeMillis() - start2) / 1000);

        executorService.shutdown();

    }
}
