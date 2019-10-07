package com.soapsnake.lab.iterator;

public class Person {

	String name;
	int age;
	int gender;

	Person(String name, int age, int gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				", gender=" + gender +
				'}';
	}
}
