package com.ld.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch doneSignal = new CountDownLatch(8);
        Executor executor = Executors.newFixedThreadPool(8);

        for (int i = 0; i < 8; i++) {
            executor.execute(new WorkerRunnable(doneSignal, i));
        }
        doneSignal.await();
    }

    static class WorkerRunnable implements Runnable {

        private final CountDownLatch doneSignal;
        private final int i;

        WorkerRunnable(CountDownLatch doneSignal, int i) {
            this.doneSignal = doneSignal;
            this.i = i;
        }

        @Override
        public void run() {
            try {
                doWork(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            doneSignal.countDown();
        }

        private void doWork(int i) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " is working on: " + i);
            Thread.sleep(1000L);
        }
    }

}
