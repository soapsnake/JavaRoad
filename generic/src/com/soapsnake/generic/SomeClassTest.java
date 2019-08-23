package com.soapsnake.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SomeClassTest {

	public static void main(String[] args) {
		SomeClass<String, Integer> someClass = new SomeClass<>();

		String k = "this is key";
		Integer v = 10;
		someClass.someMethod(k, v);

		Map<String, Integer> k1 = new HashMap<>();
		k1.put("first", 2);

		List<String> v1 = new ArrayList<>();
		v1.add("is!!!");

		SomeClass<Map, List<String>> someClass1 = new SomeClass<>();
		someClass1.someMethod(k1, v1);


		System.out.println(System.getProperty("java.library.path"));
	}
}
