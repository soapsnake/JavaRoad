package com.soapsnake.algorithms.leetcode.concurrency;

import java.util.function.IntConsumer;

public class Question1116 {


	static class  ZeroEvenOdd {
		private int n;

		public ZeroEvenOdd(int n) {
			this.n = n;
		}

		// printNumber.accept(x) outputs "x", where x is an integer.
		public void zero(IntConsumer printNumber) throws InterruptedException {
			if (n == 0) {
				printNumber.accept(n);
				n++;
			}
		}

		public void even(IntConsumer printNumber) throws InterruptedException {
			if (n /2 == 0) {
				printNumber.accept(n);
				n++;
			}
		}

		public void odd(IntConsumer printNumber) throws InterruptedException {
			if (n / 2 != 0) {
				printNumber.accept(n);
				n++;
			}
		}
	}

	static class Printer implements Runnable {

		private ZeroEvenOdd evenOdd;

		Printer(ZeroEvenOdd evenOdd, int type) {
			this.evenOdd = evenOdd;
		}

		@Override
		public void run() {
//			evenOdd.zero();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException {
		new Thread(new Runnable() {
			@Override
			public void run() {

			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {

			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {

			}
		}).start();



	}

}
