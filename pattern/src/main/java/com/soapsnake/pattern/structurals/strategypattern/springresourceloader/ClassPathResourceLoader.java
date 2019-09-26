package com.soapsnake.pattern.structurals.strategypattern.springresourceloader;

public class ClassPathResourceLoader implements LoadStrategy {

	@Override
	public Object doLoad() {
		System.out.println("classpath loader do the load work!!!!!");
		return null;
	}
}
