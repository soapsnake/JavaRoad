package com.soapsnake.lab.extend;

public class Son extends Parent {

	@Override
	public void doSome() {
		super.doSome();
		System.out.println("son can do even more.....");
	}
}
