package com.soapsnake.algorithms;

import org.apache.commons.collections4.map.HashedMap;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.HOURS;


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


    public void testTread(long time) {
        threadPoolExecutor.submit(() -> this.testRun(time));
    }

    private void testRun(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        TreadPoolTest treadPoolTest = new TreadPoolTest();
        CountDownLatch latch = new CountDownLatch(1);
        System.out.println(treadPoolTest.find());
        treadPoolTest.testTread(1);
        treadPoolTest.testTread(1000 * 60 * 60 * 5L);
        treadPoolTest.setPool(15, 100);
        Thread.sleep(1000L);
        System.out.println(treadPoolTest.find());
        latch.await();

    }

    private void setPool(int core, int queueSize) {
        threadPoolExecutor.setCorePoolSize(core);
    }
}
