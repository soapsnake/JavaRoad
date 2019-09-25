package com.soapsnake.pattern.structurals.proxy;

public abstract class DefaultInterceptor implements Interceptor {

	@Override
	public void before() {
		System.out.println("before will do nothing...");
	}

	@Override
	public void after() {
		System.out.println("after will do nothing...");
	}

	@Override
	public void around() {
		System.out.println("around will do nothing...");
	}


}
