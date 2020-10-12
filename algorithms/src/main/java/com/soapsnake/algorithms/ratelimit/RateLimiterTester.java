package com.soapsnake.algorithms.ratelimit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.RateLimiter;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-10-06
 */
public class RateLimiterTester {

    public static void main(String[] args) {
        ListeningExecutorService listeningExecutorService =
                MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(100));

        RateLimiter limiter = RateLimiter.create(1); //每秒放一个
        for (int i = 0; i < 10; i++) {
            Double acquire = null;
            if (i == 0) {
                acquire = limiter.acquire(1);
            } else if (i == 1) {
                acquire = limiter.acquire(10);  //这里第i=1个任务将会采用预制的方式运行,也就是i=1的任务会运行10秒
            } else if (i == 2) {
                acquire = limiter.acquire(2);  //第i=2个任务将会hang10秒,因为上一个任务占了10秒
            } else if (i == 3) {
                acquire = limiter.acquire(20);
            } else if (i == 4) {
                acquire = limiter.acquire(5);
            } else {
                acquire = limiter.acquire(2);
            }
            listeningExecutorService.submit(new Task("获取令牌成功,获取耗时:" + acquire + " 第 " + i + "个任务开始运行!!"));
        }
        listeningExecutorService.shutdown();
    }


    static class Task implements Runnable {
        private String msg;
        public Task(String msg) {
            this.msg = msg;
        }
        @Override
        public void run() {
            System.out.println(new Date() + Thread.currentThread().getName() + ">>>" + msg);
        }
    }
}

