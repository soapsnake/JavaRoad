package com.ld.pattern.genconpattern.generator;

public class IntgenImpl extends Intgen {
	
	private int a = 1;
	@Override
	public synchronized int next() {
		// Auto-generated method stub
		++a;
		Thread.yield();
		++a;
		return a;
	}

}
