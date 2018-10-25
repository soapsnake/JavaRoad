package com.ld.thinkinjava.classtest;

/**
 * Created by liudun on 2017/7/3.
 */
public class Son extends Parent {

    public static FieldTest2 test2 = new FieldTest2("son static field");

    static {
        System.out.println("son static");
    }

    public FieldTest test = new FieldTest("son field");

    {
        System.out.println("son non static");
    }

    public Son() {
        System.out.println("son constructor");
    }

    public void f() {
        System.out.println("son method");
    }
}
