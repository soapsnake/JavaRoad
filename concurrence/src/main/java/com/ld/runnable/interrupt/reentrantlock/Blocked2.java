package com.ld.runnable.interrupt.reentrantlock;

public class Blocked2 implements Runnable{

	@Override
	public void run() {
		BlockedMutex blockedMutex = new BlockedMutex();
		// TODO Auto-generated method stub
		System.out.println("等待BlockMutex类中的f方法");
		blockedMutex.f();
		System.out.println("BlockMutex类中的f方法执行完毕");
	}
}
