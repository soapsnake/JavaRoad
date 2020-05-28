package com.soapsnake.lab.concurrence.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch doneSignal = new CountDownLatch(20);
        ExecutorService executor = Executors.newFixedThreadPool(30);
        Semaphore semaphore = new Semaphore(5);   //最大并行任务数量
        for (int i = 0; i < 20; i++) {
            executor.execute(new WorkerRunnable(doneSignal, i, semaphore));
        }
        doneSignal.await();

        System.out.println("here = " + doneSignal.getCount());
        System.out.println(executor.isShutdown());
        System.out.println(executor.isTerminated());

        executor.shutdownNow();   //如果不关闭线程池,那么main线程将会hang在这里,永远不会结束
        System.out.println(executor.isShutdown());
        System.out.println(executor.isTerminated());

    }

    static class WorkerRunnable implements Runnable {

        private final CountDownLatch doneSignal;
        private final int i;
        private final Semaphore semaphore;

        WorkerRunnable(CountDownLatch doneSignal, int i, Semaphore semaphore) {
            this.doneSignal = doneSignal;
            this.i = i;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();   //执行任务之前需要先获取许可,如果许可只有5张,那么同时只能有5条线程可以并发

                doWork(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            doneSignal.countDown();
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + " finish work, countDownLatch count = " + doneSignal.getCount());
            System.out.println(Thread.currentThread().getName() + " finish work, semaphore permit count = " + semaphore.availablePermits());
        }

        private void doWork(int i) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " is working on: " + i);

            Thread.sleep(i * 1000L);
        }
    }

}
