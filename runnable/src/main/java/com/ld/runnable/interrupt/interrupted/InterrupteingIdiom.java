package com.ld.runnable.interrupt.interrupted;

import java.util.concurrent.TimeUnit;

public class InterrupteingIdiom {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if (args.length !=1) {
			System.out.println("usage:java Interrupting Idiom delay - in - ms");
			System.exit(1);
		}
		
		Thread thread = new Thread(new Blocked());
		thread.start();
		TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
		thread.interrupt();
	}
}
