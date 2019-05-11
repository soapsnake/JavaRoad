package com.soapsnake.pattern.structuralPatterns.templatemethod;

/**
 * Created by soapsnake on 2017/8/24.
 */
public class App {

    public static void main(String[] args) {
        Son1 son1 = new Son1();

//        son1.parentMethod();

        son1.overideMethod();
    }
}
