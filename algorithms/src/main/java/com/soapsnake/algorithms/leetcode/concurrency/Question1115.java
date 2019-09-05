package com.soapsnake.algorithms.leetcode.concurrency;

public class Question1115 {

	public static void main(String[] args) throws InterruptedException {
		int count  = 10;
		FooBar fooBar = new FooBar(count);
		Runnable printFoo = new Runnable() {
			@Override
			public void run() {
				System.out.println("foo");
			}
		};

		Runnable printBar = new Runnable() {
			@Override
			public void run() {
				System.out.println("bar");
			}
		};
		new Thread(() -> {
			try {
				fooBar.foo(printFoo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		new Thread(() -> {
			try {
				fooBar.bar(printBar);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}



	static class FooBar {
		private volatile int n;

		public FooBar(int n) {
			this.n = n;
		}
		//a抢到锁

		public synchronized void foo(Runnable printFoo) throws InterruptedException {

			for (int i = 0; i < n; i++) {

				// printFoo.run() outputs "foo". Do not change or remove this line.
				printFoo.run();
				System.out.println("i = " + i);
				notifyAll();
				System.out.println(Thread.currentThread().getName() + "准备睡了!");
				Thread.sleep(1000);
				wait();  //休眠并且释放锁
			}
		}

		public synchronized void bar(Runnable printBar) throws InterruptedException {
			for (int i = 0; i < n; i++) {
				// printBar.run() outputs "bar". Do not change or remove this line.
					printBar.run();
					notifyAll();
				System.out.println(Thread.currentThread().getName() + "准备睡了!");
				Thread.sleep(1000);
				wait();
			}
		}
	}
}
