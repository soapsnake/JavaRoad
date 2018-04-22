package com.ld.multithread.concurrce;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
	private Lock banklock;
	private final double[] accounts;
	private Condition sufficientFunds;

	public Bank(int n, double initialBalance) {
		accounts = new double[n];
		Arrays.fill(accounts, initialBalance); // fill方法:把后者填进前者数组里面

		 banklock = new ReentrantLock();
		 sufficientFunds = banklock.newCondition();
	}

	 public void transfer(int from,int to, double amount){
	 banklock.lock();
	 try {
	 while(accounts[from]<amount){
	 System.out.println("this account have money: "+accounts[from]+" less than amount: "+amount);
	 //sufficientFunds.awaitUninterruptibly();   //这个方法,等锁线程可以被interrupt从而防止死锁
	 sufficientFunds.await(); //注意这里是await方法而不是wait方法
	 }
	 //source account have no enough money
	 System.out.println(Thread.currentThread());
	
	 accounts[from] -= amount; //get money from source account
	 System.out.printf("%10.2f from %d to %d",amount,from,to); //what is printf
	
	 accounts[to] += amount; //give money to destiny account
	 System.out.printf("Total Balance: %10.2f%n",getTotalBalance());
//	 sufficientFunds.signalAll();
	 sufficientFunds.signal();      //实测该方法会导致死锁!!!!
	 //只要账户发生了变动,就应该给wait的线程一个机会,让他们自己去检查自己是否能够继续运行
	 } catch (InterruptedException e) {
	 //Auto-generated method stub
	 e.printStackTrace();
	 } finally {
	 //: handle finally clause
	 banklock.unlock();
	 }
	
	 }

//	public  void transfer(int from, int to, double amount) {
//		try {
//			while (accounts[from] < amount) {
//				System.out.println("this account have money: " + accounts[from] + " less than amount: " + amount);
//				wait();
//			}
//			// source account have no enough money
//			System.out.println(Thread.currentThread());
//
//			accounts[from] -= amount; // get money from source account
//			System.out.printf("%10.2f from %d to %d", amount, from, to); // what is printf
//			accounts[to] += amount; // give money to destiny account
//			System.out.printf("Total Balance: %10.2f%n", getTotalBalance());
//	        notifyAll(); // 只要账户发生了变动,就应该给wait的线程一个机会,让他们自己去检查自己是否能够继续运行
//			
//		} catch (InterruptedException e) {
//			//Auto-generated method stub
//			e.printStackTrace();
//		} 
//	}
	
	public synchronized double getTotalBalance() {
		double sum = 0;
		for (double a : accounts) // foreach every account & sum them,return all
									// money in this bank
			sum += a;
		return sum;
	}

	public synchronized int size() {
		return accounts.length;
	}

}
