package com.soapsnake.thinkinjava.generics;

/**
 * Created by liudun on 2017/7/9.
 */
public class FactoryConstraint {
    public static void main(String[] args) {
        new Foo2<Integer>(new IntegerFactory());

        new Foo2<Widget>(new Widget.Factory());
    }
}
