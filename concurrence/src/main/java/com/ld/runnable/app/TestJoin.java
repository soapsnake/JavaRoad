package com.ld.runnable.app;

import com.ld.runnable.thread.JoinThread;

public class TestJoin {

	public static void main(String[] args) {
		JoinThread jThread = new JoinThread();
		Thread thread = new Thread(jThread);
		
		thread.start();
		try {
			thread.join();      //此处的join方法可以指定超时时间,如果不指定的话该线程会一直等到任务线程结束
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName()+": is running");
	}
}
