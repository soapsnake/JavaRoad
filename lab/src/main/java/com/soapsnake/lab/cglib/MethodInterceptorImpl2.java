package com.soapsnake.lab.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MethodInterceptorImpl2 implements MethodInterceptor {
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy proxy) throws Throwable {

		System.err.println("MethodInterceptorImpl2 == > Before invoke " + method);   //前置增强逻辑
		Object result = proxy.invokeSuper(o, objects);   //执行代理对象的原始逻辑
		System.err.println("MethodInterceptorImpl2 ==> After invoke" + method);    //后置增强逻辑
		return result;
	}
}
