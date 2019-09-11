package com.soapsnake.lambda.stream.flatmap;

import com.soapsnake.lambda.stream.Person;

import java.util.Arrays;
import java.util.List;

public class FlatMap2 {

	public static void main(String[] args) {

		Person zhangsan = new Person(1, 15, "张三", null);
		Person lisi = new Person(2, 18, "李四", null);
		List<Person> friends = Arrays.asList(zhangsan, lisi);

		Person limei =  new Person(0, 12,"limei", friends);


		List<Person> friends2 = Arrays.asList(lisi, limei);
		Person wangwu = new Person(1, 14, "王五", friends2);


		List<Person> persons = Arrays.asList(limei, wangwu);


	}
}
