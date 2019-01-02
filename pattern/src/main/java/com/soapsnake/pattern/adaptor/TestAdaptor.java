package com.soapsnake.pattern.adaptor;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-02 11:25
 */
public class TestAdaptor {

    public static void main(String[] args) {
        TargetInterface target = new TargetObj();

        target.method1();
        target.method2();
        target.method3();
    }
}
