package com.ld.runnable.thread;

import java.io.IOException;
import java.io.InputStreamReader;

public class UnresponsiveUI {
	private volatile double d = 1;
	public UnresponsiveUI() throws IOException{
		while(d > 0){
			d = d + (Math.PI + Math.E) / d;
			System.out.println(d);
			System.out.println("input: ");
			System.out.println(System.in.read());
		}
	}
}
