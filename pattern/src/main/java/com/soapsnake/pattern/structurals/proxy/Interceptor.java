package com.soapsnake.pattern.structurals.proxy;

public interface Interceptor {

	void before();

	void after();

	void around();

}
