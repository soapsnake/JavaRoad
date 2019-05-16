package com.soapsnake.pattern.creationalPatterns.builder;

public abstract class Parent {

    static {
        System.out.println("parent static");
    }

    Parent() {
        System.out.println("parent constructor");
    }

    Parent(String msg) {
        System.out.println("parent constructor with msg");
    }


}
