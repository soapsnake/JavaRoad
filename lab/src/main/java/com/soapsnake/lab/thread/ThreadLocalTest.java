package com.soapsnake.lab.thread;

public class ThreadLocalTest {


	public static class MyRunnable1 implements Runnable {

		private Person person;

		public MyRunnable1(Person person) {
			this.person = person;
		}

		@Override
		public void run() {
			System.out.println("原名::" + person.name);
			System.out.println("源laocal::" + person.threadLocal.get());

			try {
				Thread.sleep(2000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "----->" + person.name);
			System.out.println(Thread.currentThread().getName() + "----->" + person.threadLocal.get());
		}
	}

	public static class MyRunnable2 implements Runnable {

		private Person person;

		public MyRunnable2(Person person) {
			this.person = person;
		}

		@Override
		public void run() {
			System.out.println("原名::" + person.name);

			System.out.println(Thread.currentThread().getName());
			System.out.println(person.threadLocal.get());
			person.threadLocal.set("线程2设置的threadlocal");  //设置person的threadlocal变量
			person.name = "王晓刚";   //设置person的name变量
			System.out.println(person.threadLocal.get());
			System.out.println("person已经被改名为:::" + person.name);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("start");
		Person person = new Person();
		person.threadLocal.set("小明local");
		person.name = "某某某";


		Thread thread1 = new Thread(new MyRunnable1(person));
		Thread thread2 = new Thread(new MyRunnable2(person));
		thread1.start();
		thread2.start();



		Thread.sleep(5000L);
		System.out.println(person.name);
		System.out.println(person.threadLocal.get());

	}




}
