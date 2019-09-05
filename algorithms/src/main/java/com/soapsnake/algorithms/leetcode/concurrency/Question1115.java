package com.soapsnake.algorithms.leetcode.concurrency;

public class Question1115 {

	public static void main(String[] args) throws InterruptedException {
		int count  = 1;
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

		fooBar.foo(printFoo);
		fooBar.bar(printBar);

	}



	static class FooBar {
		private int n;
		private boolean should;

		public FooBar(int n) {
			this.n = n;
			this.should = false;
		}

		public synchronized void foo(Runnable printFoo) throws InterruptedException {

			for (int i = 0; i < n; i++) {

				// printFoo.run() outputs "foo". Do not change or remove this line.
				printFoo.run();
				should = true;
				wait();
			}
		}

		public synchronized void bar(Runnable printBar) throws InterruptedException {

			for (int i = 0; i < n; i++) {

				// printBar.run() outputs "bar". Do not change or remove this line.
				printBar.run();
			}
		}
	}
}
