package com.soapsnake.lab.reflact;

import javax.annotation.Resource;
import javax.management.MXBean;

@MXBean
@Resource
public class ReflactTester {

	private String name;

	public int age;

	public ReflactTester(){}

	public static void main(String[] args) {
		Object o = new Derive();
		test(o);
	}

	public static void test(Object x) {
		System.out.println(x.getClass());
		Class<?> Type = x.getClass();
		System.out.println(Type + " " +"instance of Base => " + (x instanceof Base));

		System.out.println("x instance of Derive =>" + (x instanceof  Derive));

		System.out.println("Base.class isInstanceOf x => " + (Base.class.isInstance(x)));

		System.out.println("Derive.class isInstanceOf x =>  " + (Derive.class.isInstance(x)));

		System.out.println("Base.class = x.getclass => " + (Base.class == x.getClass()));

		System.out.println("Derive.class == x.getClass => " + (Derive.class == x.getClass()));

		System.out.println("Derive.class.equals(x.getclass) => " + Derive.class.equals(x.getClass()));

		System.out.println("Base.class.equals(x.getclass) => " + Base.class.equals(x.getClass()));
	}
}

class Base{}

class Derive extends Base {}
