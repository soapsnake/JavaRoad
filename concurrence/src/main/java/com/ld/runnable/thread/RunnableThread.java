package com.ld.runnable.thread;

public class RunnableThread implements Runnable {
	
	public RunnableThread(){}
	
	@Override
	public void run() {
		int i = 0;
		while(i<=2){
			System.out.println(Thread.currentThread().getName()+"  run a run"+ " "+ i++);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
