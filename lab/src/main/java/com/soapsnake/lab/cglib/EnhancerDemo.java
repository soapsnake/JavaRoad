package com.soapsnake.lab.cglib;

import com.alibaba.fastjson.JSON;
import net.sf.cglib.proxy.Enhancer;

public class EnhancerDemo {

	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(EnhancerDemo.class);  //enhancer记录了父类信息
		enhancer.setCallback(new MethodInterceptorImpl());   //enhancer记录了代理函数

		EnhancerDemo enhancerDemo = (EnhancerDemo) enhancer.create();   //利用enhancer创建代理类,其实代理类是EnhancerDemo的子类

		System.out.println("demo " + JSON.toJSON(enhancerDemo));  //从打印信息可见这个enhancerDemo是一个proxy,是由cglib动态生成的

		enhancerDemo.test();

		System.out.println(enhancerDemo);
	}

	public void test() {
		System.out.println("EnhancerDemo.test()");
	}
}
