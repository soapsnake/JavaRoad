package com.ld.genconpattern.generator;

public abstract class Intgen {
		
	private volatile boolean canceled = false;
	
	public abstract int next();
	
	public void cancel(){
		canceled = true;
	}
	
	public boolean isCanceled(){
		return canceled;
	}
	
}
