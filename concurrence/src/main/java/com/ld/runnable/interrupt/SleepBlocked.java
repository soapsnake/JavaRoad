package com.ld.runnable.interrupt;

import java.util.concurrent.TimeUnit;

public class SleepBlocked implements Runnable{ //睡眠工作线程

	@Override
	public void run() {
		// Auto-generated method stub
		try {
			TimeUnit.SECONDS.sleep(100);         //睡眠100秒
		} catch (InterruptedException e) {
			System.out.println("线程的睡眠状态遭遇异常!!!!!!!!!!!!!!!!!");
		}
		
		System.out.println("Exiting sleepBolcked.run()");
	}
}
