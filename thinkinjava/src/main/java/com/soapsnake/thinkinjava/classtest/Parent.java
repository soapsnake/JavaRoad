package com.soapsnake.thinkinjava.classtest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soapsnake on 2017/7/3.
 */
public class Parent {

    public static FieldTest2 test2 = new FieldTest2("parent static field");
    public static List<?> anyList = new ArrayList<>();

    static {
        System.out.println("parent static");

    }

    public FieldTest test = new FieldTest("parent field");

    {
        System.out.println("parent non static");
    }

    Parent() {
        System.out.println("parent constructor");
    }

    void f() {
        System.out.println("parent method");
    }

}
