package com.soapsnake.algorithms.utils;

public class Person {

    public int age = 1;
    public String name;

    public static void main(String[] args) {
        Person person = new Person();
        ++person.age;
        System.out.println(person.age);

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
