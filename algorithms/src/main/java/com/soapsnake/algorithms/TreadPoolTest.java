package com.soapsnake.algorithms;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.map.HashedMap;


/**
 * @author 
 * Created on 2022-01-21
 */
public class TreadPoolTest {
    private final ThreadPoolExecutor threadPoolExecutor;

    public TreadPoolTest () {
        threadPoolExecutor =
                new ThreadPoolExecutor(12, 32, 0L,
                        TimeUnit.MINUTES, new LinkedBlockingQueue<>(123));
    }

    public Map<Object, Object> find () {
        Map<Object, Object> map = new HashedMap<>();
        map.put("corePoolSize", threadPoolExecutor.getCorePoolSize());
        map.put("queueSize", threadPoolExecutor.getQueue());
        map.put("task", threadPoolExecutor.getTaskCount());
        map.put("taskComplete", threadPoolExecutor.getCompletedTaskCount());
        return map;
    }


    public void testTread(long time, CountDownLatch latch) {
        threadPoolExecutor.submit(() -> this.testRun(time, latch));
    }

    private void testRun(long time, CountDownLatch latch) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        TreadPoolTest treadPoolTest = new TreadPoolTest();
        CountDownLatch latch = new CountDownLatch(1);
        System.out.println(treadPoolTest.find());
        treadPoolTest.testTread(1, latch);
        treadPoolTest.setPool(15, 100);
        Thread.sleep(1000L);
        System.out.println(treadPoolTest.find());
        System.out.println("Hello there!!!");
        latch.await();

    }

    private void setPool(int core, int queueSize) {
        threadPoolExecutor.setCorePoolSize(core);
    }
}
