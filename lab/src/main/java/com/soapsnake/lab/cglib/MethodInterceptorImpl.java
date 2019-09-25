package com.soapsnake.lab.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MethodInterceptorImpl implements MethodInterceptor {
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy proxy) throws Throwable {

		System.err.println("Before invoke " + method);

		Object result = proxy.invokeSuper(o, objects);

		System.err.println("After invoke" + method);

		return result;
	}
}
