package com.soapsnake.pattern.creationals.builder;

public class Son extends Parent  {

    static {
        System.out.println("son static");
    }

    Son() {
        super("123");
        System.out.println("son constructor");
    }


}
