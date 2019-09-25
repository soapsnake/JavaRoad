package com.soapsnake.pattern.structurals.proxy;

public class SomeInterceptor extends DefaultInterceptor {

	@Override
	final public void before() {
		System.out.println("before to real, we can check something...");
	}
}
