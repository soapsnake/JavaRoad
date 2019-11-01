package com.soapsnake.lab.thread;

public class Person {

    public final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public String name;
    public Integer age;
    public Integer gender;
}
