package com.soapsnake.concurrence.stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {

	public static void main(String[] args) {
		List<Person> list = new ArrayList<>();
		for (int i = 0; i < 200; i++) {
			Person person = new Person();
			person.setAge(RandomUtils.nextInt(1,100));
			person.setName(RandomStringUtils.randomAlphabetic(5));
			list.add(person);
		}
		list.parallelStream().forEach(o -> {
			System.out.println(Thread.currentThread().getName());
			System.out.println(o.toString());
			throw new RuntimeException("fdajifda");
		});
	}
}
