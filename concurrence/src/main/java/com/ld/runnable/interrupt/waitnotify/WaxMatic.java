package com.ld.runnable.interrupt.waitnotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaxMatic {

	public static void main(String[] args) throws Exception{
		// Auto-generated method stub
		Car car = new Car();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new WaxOff(car));
		executorService.execute(new WaxOn(car));
		TimeUnit.SECONDS.sleep(5);
		executorService.shutdown();
	}
}
