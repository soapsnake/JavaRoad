package com.soapsnake.lab.thread;

public class Person {

	public String name;

	public Integer age;

	public Integer gender;

	public final ThreadLocal<String> threadLocal = new ThreadLocal<>();
}
