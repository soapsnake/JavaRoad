package com.ld.runnable.interrupt.interrupted;

import java.util.concurrent.TimeUnit;

public class Blocked implements Runnable{
	
	private volatile double d = 0.0;
	@Override
	public void run() {
		// Auto-generated method stub
		try {
			while(!Thread.interrupted()){
				NeedsCleanup n1 = new NeedsCleanup(1);
				try {
					System.out.println("sleeping");
					TimeUnit.SECONDS.sleep(1);
					
					NeedsCleanup n2 = new NeedsCleanup(2);
					try {
						System.out.println("calcalating");
						for(int i=1;i<2500000;i++)
							d = d + (Math.PI +Math.E) /d;
						System.out.println("finished time-consuming operation");
					} finally {
						//: handle exception
						n2.cleanup();
					}
				} finally {
					//: handle exception
					n1.cleanup();
				}
			}
			System.out.println("Exiting via while() test");
		} catch (Exception e) {
			//: handle exception
			System.out.println("Exiting via InterruptedException");
		}
	}
}
