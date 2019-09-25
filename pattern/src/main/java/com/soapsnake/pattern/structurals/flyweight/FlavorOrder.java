package com.soapsnake.pattern.structurals.flyweight;

public class FlavorOrder extends Order {

	private String flavor;

	public FlavorOrder(String flavor) {
		this.flavor = flavor;
	}

	@Override
	public void sell() {
		System.out.println("卖出一杯["+this.flavor+"]口味的咖啡");
	}

	@Override
	public String getFlavor() {
		return this.flavor;
	}
}
