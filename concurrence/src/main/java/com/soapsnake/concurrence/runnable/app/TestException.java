package com.soapsnake.concurrence.runnable.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.soapsnake.concurrence.runnable.factory.HandlerThreadFactory;
import com.soapsnake.concurrence.runnable.thread.ExceptionThread2;

public class TestException {

    public static void main(String[] args) {
//		ExecutorService executor = Executors.newCachedThreadPool();
//		try {
//			executor.execute(new ExceptionThread());    //无法在当前线程中捕获其他线程的异常
//		} catch (Exception e) {
//			System.out.println("i got the problem!!!!!!!!!");
//			e.printStackTrace();
//		}
        //把带有异常处理handler的factory交给线程池对象
        ExecutorService executor = Executors.newCachedThreadPool(new HandlerThreadFactory());
        executor.execute(new ExceptionThread2());
    }
}
