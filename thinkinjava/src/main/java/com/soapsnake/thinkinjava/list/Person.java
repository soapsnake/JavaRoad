package com.soapsnake.thinkinjava.list;

/**
 * Created by soapsnake on 2017/7/11.
 */
public class Person implements Comparable<Person> {

    String name;
    int status;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        return this.status - o.status;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", age=" + age +
                '}';
    }


}
