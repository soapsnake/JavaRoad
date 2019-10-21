package com.soapsnake.pattern.creationals.singleton;

public class SingleTester {

    public static void main(String[] args) {
        SingletonClass singletonClass = SingletonClass.getInstance();

        System.out.println(singletonClass);

        SingletonClass singletonClass1 = SingletonClass.getInstance();

        System.out.println(singletonClass1);

        System.out.println(singletonClass == singletonClass1);
    }
}
