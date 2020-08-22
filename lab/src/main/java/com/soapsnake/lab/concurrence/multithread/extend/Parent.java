package com.soapsnake.lab.concurrence.multithread.extend;

public class Parent {

    private static final String NAME = "parent";

    void function() {
        System.out.println("this is the parent's onw method,but son will override it");
    }

    void function0() {

        System.out.println("this is a method own by parent only");
    }

    void funcitionOfParent() {
        System.out.println("this is a function from parent!!!!");
    }

}
