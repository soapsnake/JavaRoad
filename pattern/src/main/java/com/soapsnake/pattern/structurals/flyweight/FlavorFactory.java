package com.soapsnake.pattern.structurals.flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FlavorFactory {
	private static final Map<String, Order> pool = new ConcurrentHashMap<>();
	public static FlavorFactory getInstance() {
		return new FlavorFactory();
	}

	public static int size() {
		return pool.size();
	}

	private FlavorFactory() {
	}

	public Order getOrder(String flavor) {
		if (pool.containsKey(flavor)) {
			return pool.get(flavor);
		} else {
			Order order = new FlavorOrder(flavor);
			order.sell();
			pool.put(flavor, order);
			return order;
		}
	}
}
