package com.soapsnake.lab.concurrence.multithread.extend;

public class Son extends Parent implements SonOtherParent {

    private static final String NAME = "son";

    @Override
    void function() {
        System.out.println("this is a method override his parent");
    }

    void function1() {
        System.out.println("this is a method own by son");
    }

    @Override
    public void function2(int number) {
        // Auto-generated method stub
        System.out.println("this is a method implement a interface");
    }
}
