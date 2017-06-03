package com.ld.runnable.interrupt.reentrantlock;

import java.util.concurrent.TimeUnit;

public class Interruping {
	
	public static void main(String[] args) throws Exception{
		
		Thread thread = new Thread(new Blocked2());
		thread.start();
		TimeUnit.SECONDS.sleep(2);
		System.out.println("准备对线程发送中断请求了!!!!!!!!!!");
		thread.interrupt();
	}
}
