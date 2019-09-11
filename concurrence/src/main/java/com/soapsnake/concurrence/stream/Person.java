package com.soapsnake.concurrence.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {

	private String name;
	private Integer age;
	private List<Person> friends = new ArrayList<>();

	public static void main(String[] args) {
		int i1 = 10;
		int i2 = 10;

		int i3 = 300;
		int i4 = 300;

		Integer i5 = 11;
		Integer i6 = 11;

		Integer i7 = 130;
		Integer i8 = 130;

		System.out.println(i1 ==  i2);
		System.out.println(i3 ==  i4);
		System.out.println(i5 ==  i6);
		System.out.println(i7 ==  i8);

		List<Person> personList = new ArrayList<>();
		Person zhangsan = new Person("zhangsan", 15, null);
		Person lisi = new Person("lisi", 19, null);
		Person limei = new Person("limei", 21, Arrays.asList(zhangsan, lisi));
		personList.add(limei);
		personList.stream().flatMap(o -> Stream.of(o.getFriends())).forEach(System.out::println);
	}
}
