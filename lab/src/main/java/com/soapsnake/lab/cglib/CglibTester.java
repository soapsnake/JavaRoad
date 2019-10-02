package com.soapsnake.lab.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

public class CglibTester {

	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();  //cglib 的Enhancer
		enhancer.setSuperclass(ProxySource.class);  //这个superclass其实就是要被代理的类了,因为创建的代理类是继承被代理类的


//		enhancer.setCallback(new MethodInterceptorImpl());   //enhancer记录了代理函数
//		enhancer.setCallback(NoOp.INSTANCE);   //🍎这种设置callback的方式会发生覆盖而不是追加

		Callback[] callbacks = {new MethodInterceptorImpl(), new MethodInterceptorImpl2()};  //这种设置方式可以一次设置多个callback

		enhancer.setCallbacks(callbacks);   //设置多个callbacks但是不设置filter的话将会抛出异常

		enhancer.setCallbackFilter(new CallBackFilter());

		ProxySource enhancerDemo = (ProxySource) enhancer.create();   //利用enhancer创建代理类,其实代理类是EnhancerDemo的子类
//		System.out.println("demo " + JSON.toJSON(enhancerDemo));  //从打印信息可见这个enhancerDemo是一个proxy,是由cglib动态生成的

		enhancerDemo.test();
		enhancerDemo.anotherMethod();
	}

}
