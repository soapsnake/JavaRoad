package com.soapsnake.pattern.structurals.strategypattern.springresourceloader;

public class DefaultOtherThingsStrategy implements OtherStrategy {


	@Override
	public void doOthers() {
		System.out.println("DefaultOtherThingsStrategy will doOthers()");
	}
}
